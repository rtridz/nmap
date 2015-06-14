package ru.bmstu.tp.nmapclient.Services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import ru.bmstu.tp.nmapclient.DataBase.DBContentProvider;
import ru.bmstu.tp.nmapclient.DataBase.DBSchemas;
import ru.bmstu.tp.nmapclient.Services.Exceptions.BadRequestException;
import ru.bmstu.tp.nmapclient.Services.Exceptions.BadSessionIdException;
import ru.bmstu.tp.nmapclient.Services.Exceptions.InitialServerException;
import ru.bmstu.tp.nmapclient.Services.Exceptions.ServerConnectException;
import ru.bmstu.tp.nmapclient.Services.SQLReader.NmapScanResult;

public class APIService extends IntentService {
    public enum Action {
        REGISTR, CHECK_SERVER, SEND_REQUEST
    }
    private NmapAPISender sender;

    public APIService() {
        super("APIService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        switch (Action.valueOf(intent.getAction())) {
            case REGISTR :
                registrateApp(intent);
                break;
            case CHECK_SERVER :
                checkServerConnect(intent);
                break;
            case SEND_REQUEST :
                sendAPIRequest(intent);
                break;
        }
    }

    private void registrateApp(Intent intent) {
        if (sender == null) {
            sender = new NmapAPISender(0);
        }
        try {
             sender.updateSessionId();
        } catch (ServerConnectException | InitialServerException e) {
            e.printStackTrace();
            // после splash activity открыть history
        }
        ContentValues cv = new ContentValues();
        cv.put(DBSchemas.Info.USER_ID, sender.getUserId());
        Cursor cursor = getContentResolver().query(DBContentProvider.INFO_URI, new String[]{"_id"}, null, null, null);
        if (cursor.moveToFirst()) {
            getContentResolver().update(DBContentProvider.INFO_URI, cv, DBSchemas.Info.USER_ID + " = ?", new String[]{cursor.getString(0)});
        } else {
            getContentResolver().insert(DBContentProvider.INFO_URI, cv);
        }

        try {
            sender.sendGcmId(intent.getStringExtra("data"));
        } catch (ServerConnectException | InitialServerException e) {
            e.printStackTrace();
            // после splash activity открыть history
        } catch (BadSessionIdException | BadRequestException e) {
            e.printStackTrace();
        }
    }

    private void checkServerConnect(Intent intent) {
        if (sender == null) {
            Cursor cursor = getContentResolver().query(DBContentProvider.INFO_URI, new String[]{DBSchemas.Info.USER_ID}, null, null, null);
            if (cursor.moveToFirst()) {
                sender = new NmapAPISender(cursor.getInt(0));
            }
        }
        try {
            sender.sendCheckRequest();
        } catch (ServerConnectException | InitialServerException e) {
            e.printStackTrace();
            // после splash activity открыть history
        }
    }

    private void sendAPIRequest(Intent intent) {
        if (sender == null) {
            Cursor cursor = getContentResolver().query(DBContentProvider.INFO_URI, new String[]{DBSchemas.Info.USER_ID}, null, null, null);
            if (cursor.moveToFirst()) {
                sender = new NmapAPISender(cursor.getInt(0));
            }
        }
        NmapScanResult result = null;
        try {
            result = sender.sendAPIRequest(intent.getStringExtra("data"));
        } catch (ServerConnectException e) {
            e.printStackTrace();
        } catch (InitialServerException e) {
            e.printStackTrace();
        } catch (BadSessionIdException e) {
            e.printStackTrace();
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
        if (result != null) {
            //----------- save scan --------------
            ContentValues cv = new ContentValues();
            cv.put(DBSchemas.Scans.TIME, result.getStart().toString());
            cv.put(DBSchemas.Scans.COMMAND, result.getArgs());
            getContentResolver().insert(DBContentProvider.SCANS_URI, cv);

            //------------ save temp_scan -------------
            cv = new ContentValues();
            cv.put(DBSchemas.Scans.TIME, result.getStart().toString());
            cv.put(DBSchemas.Scans.COMMAND, result.getArgs());
            getContentResolver().update(DBContentProvider.TEMP_SCAN_URI, cv);

            //------------ save hosts -------------

            //------------ save host_names -------------

            //------------ save ports -------------

        }
    }
}
