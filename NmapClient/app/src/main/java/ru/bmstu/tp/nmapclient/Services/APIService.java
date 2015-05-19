package ru.bmstu.tp.nmapclient.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import ru.bmstu.tp.nmapclient.Services.Exceptions.BadRequestException;
import ru.bmstu.tp.nmapclient.Services.Exceptions.BadSessionIdException;
import ru.bmstu.tp.nmapclient.Services.Exceptions.InitialServerException;
import ru.bmstu.tp.nmapclient.Services.Exceptions.ServerConnectException;

public class APIService extends IntentService {
    public enum Action {
        REGISTR, CHECK_SERVER, SEND_REQUEST
    }

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
        try {
            NmapAPISender.updateSessionId();
        } catch (ServerConnectException e) {
            e.printStackTrace();
        } catch (InitialServerException e) {
            e.printStackTrace();
        }

        // сохранить id в БД

        try {
            NmapAPISender.sendGcmId(intent.getStringExtra("data"));
        } catch (ServerConnectException e) {
            e.printStackTrace();
        } catch (InitialServerException e) {
            e.printStackTrace();
        } catch (BadSessionIdException e) {
            e.printStackTrace();
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
    }

    private void checkServerConnect(Intent intent) {
        NmapAPISender.setSessionId(intent.getLongExtra("data", 0L));
        try {
            NmapAPISender.sendCheckRequest();
        } catch (ServerConnectException e) {
            e.printStackTrace();
        } catch (InitialServerException e) {
            e.printStackTrace();
        }
    }

    private void sendAPIRequest(Intent intent) {
        try {
            NmapAPISender.sendAPIRequest(intent.getStringExtra("data"));
        } catch (ServerConnectException e) {
            e.printStackTrace();
        } catch (InitialServerException e) {
            e.printStackTrace();
        } catch (BadSessionIdException e) {
            e.printStackTrace();
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
    }
}
