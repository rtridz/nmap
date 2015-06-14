package ru.bmstu.tp.nmapclient.DataBase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class DBContentProvider extends ContentProvider {
    static final String LOG_TAG = "DBContentProvider";
    static final String AUTHORITY = "ru.bmstu.tp.nmapclient.DBContentProvider";

    static final String INFO_PATH = "info";
    public static final Uri INFO_URI = Uri.parse("content://" + AUTHORITY + "/" + INFO_PATH);
    static final String INFO_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + INFO_PATH;
    static final int URI_INFO = 1;

    static final String TEMP_SCAN_PATH = "temp_scan";
    public static final Uri TEMP_SCAN_URI = Uri.parse("content://" + AUTHORITY + "/" + TEMP_SCAN_PATH);
    static final String TEMP_SCAN_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + TEMP_SCAN_PATH;
    static final int URI_TEMP_SCAN = 2;

    static final String SCANS_PATH = "scans";
    public static final Uri SCANS_URI = Uri.parse("content://" + AUTHORITY + "/" + SCANS_PATH);
    static final String SCANS_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + SCANS_PATH;
    static final String SCANS_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + SCANS_PATH;
    static final int URI_SCANS = 3;
    static final int URI_SCANS_ID = 4;

    static final String HOSTS_PATH = "hosts";
    public static final Uri HOSTS_URI = Uri.parse("content://" + AUTHORITY + "/" + HOSTS_PATH);
    static final String HOSTS_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + HOSTS_PATH;
    static final String HOSTS_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + HOSTS_PATH;
    static final int URI_HOSTS = 5;
    static final int URI_HOSTS_ID = 6;

    static final String PORTS_PATH = "ports";
    public static final Uri PORTS_URI = Uri.parse("content://" + AUTHORITY + "/" + PORTS_PATH);
    static final String PORTS_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + PORTS_PATH;
    static final String PORTS_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + PORTS_PATH;
    static final int URI_PORTS = 7;
    static final int URI_PORTS_ID = 8;

    static final String HOSTNAMES_PATH = "host_names";
    public static final Uri HOSTNAMES_URI = Uri.parse("content://" + AUTHORITY + "/" + HOSTNAMES_PATH);
    static final String HOSTNAMES_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + HOSTNAMES_PATH;
    static final String HOSTNAMES_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + HOSTNAMES_PATH;
    static final int URI_HOSTNAMES = 9;
    static final int URI_HOSTNAMES_ID = 10;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, INFO_PATH, URI_INFO);
        uriMatcher.addURI(AUTHORITY, TEMP_SCAN_PATH, URI_TEMP_SCAN);
        uriMatcher.addURI(AUTHORITY, SCANS_PATH, URI_SCANS);
        uriMatcher.addURI(AUTHORITY, SCANS_PATH + "/#", URI_SCANS_ID);
        uriMatcher.addURI(AUTHORITY, HOSTS_PATH, URI_HOSTS);
        uriMatcher.addURI(AUTHORITY, HOSTS_PATH + "/#", URI_HOSTS_ID);
        uriMatcher.addURI(AUTHORITY, PORTS_PATH, URI_PORTS);
        uriMatcher.addURI(AUTHORITY, PORTS_PATH + "/#", URI_PORTS_ID);
        uriMatcher.addURI(AUTHORITY, HOSTNAMES_PATH, URI_HOSTNAMES);
        uriMatcher.addURI(AUTHORITY, HOSTNAMES_PATH + "/#", URI_HOSTNAMES_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(LOG_TAG, "delete: " + uri.toString());
        dbHelper.getWritableDatabase();
        int cnt;
        String id;
        switch (uriMatcher.match(uri)) {
            case URI_INFO :
//                cnt = db.delete(DBSchemas.Info.TABLE_NAME, selection, selectionArgs);
//                getContext().getContentResolver().notifyChange(uri, null);
//                return cnt;
                throw new IllegalArgumentException("Info couldn't been deleted!!!  Uri: " + uri);
            case URI_TEMP_SCAN :
                cnt = db.delete(DBSchemas.TempScan.TABLE_NAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            case URI_SCANS_ID :
                id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "delete scan with id: " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = "_id = " + id;
                } else {
                    selection = selection + " AND _id = " + id;
                }
            case URI_SCANS :
                cnt = db.delete(DBSchemas.Scans.TABLE_NAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            case URI_HOSTS_ID :
                id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "delete host with id: " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = "_id = " + id;
                } else {
                    selection = selection + " AND _id = " + id;
                }
            case URI_HOSTS :
                cnt = db.delete(DBSchemas.Hosts.TABLE_NAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            case URI_PORTS_ID :
                id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "delete port with id: " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = "_id = " + id;
                } else {
                    selection = selection + " AND _id = " + id;
                }
            case URI_PORTS :
                cnt = db.delete(DBSchemas.Ports.TABLE_NAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            case URI_HOSTNAMES_ID :
                id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "delete hostname with id: " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = "_id = " + id;
                } else {
                    selection = selection + " AND _id = " + id;
                }
            case URI_HOSTNAMES :
                cnt = db.delete(DBSchemas.HostNames.TABLE_NAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            default:
                throw new IllegalArgumentException("Wrong Uri: " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        Log.d(LOG_TAG, "getType: " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_INFO :
                return INFO_ITEM_TYPE;
            case URI_TEMP_SCAN :
                return TEMP_SCAN_ITEM_TYPE;
            case URI_SCANS :
                return SCANS_TYPE;
            case URI_SCANS_ID :
                return SCANS_ITEM_TYPE;
            case URI_HOSTS :
                return HOSTS_TYPE;
            case URI_HOSTS_ID :
                return HOSTS_ITEM_TYPE;
            case URI_PORTS :
                return PORTS_TYPE;
            case URI_PORTS_ID :
                return PORTS_ITEM_TYPE;
            case URI_HOSTNAMES :
                return HOSTNAMES_TYPE;
            case URI_HOSTNAMES_ID :
                return HOSTNAMES_ITEM_TYPE;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(LOG_TAG, "insert: " + uri.toString());
        dbHelper.getWritableDatabase();
        long rowID;
        Uri resultUri;
        switch (uriMatcher.match(uri)) {
            case URI_INFO :
                rowID = db.insert(DBSchemas.Info.TABLE_NAME, null, values);
                resultUri = ContentUris.withAppendedId(INFO_URI, rowID);
                getContext().getContentResolver().notifyChange(resultUri, null);
                return resultUri;
            case URI_TEMP_SCAN :
                rowID = db.insert(DBSchemas.TempScan.TABLE_NAME, null, values);
                resultUri = ContentUris.withAppendedId(TEMP_SCAN_URI, rowID);
                getContext().getContentResolver().notifyChange(resultUri, null);
                return resultUri;
            case URI_SCANS :
            case URI_SCANS_ID :
                rowID = db.insert(DBSchemas.Scans.TABLE_NAME, null, values);
                resultUri = ContentUris.withAppendedId(SCANS_URI, rowID);
                getContext().getContentResolver().notifyChange(resultUri, null);
                return resultUri;
            case URI_HOSTS :
            case URI_HOSTS_ID :
                rowID = db.insert(DBSchemas.Hosts.TABLE_NAME, null, values);
                resultUri = ContentUris.withAppendedId(HOSTS_URI, rowID);
                getContext().getContentResolver().notifyChange(resultUri, null);
                return resultUri;
            case URI_PORTS :
            case URI_PORTS_ID :
                rowID = db.insert(DBSchemas.Ports.TABLE_NAME, null, values);
                resultUri = ContentUris.withAppendedId(PORTS_URI, rowID);
                getContext().getContentResolver().notifyChange(resultUri, null);
                return resultUri;
            case URI_HOSTNAMES :
            case URI_HOSTNAMES_ID :
                rowID = db.insert(DBSchemas.HostNames.TABLE_NAME, null, values);
                resultUri = ContentUris.withAppendedId(HOSTNAMES_URI, rowID);
                getContext().getContentResolver().notifyChange(resultUri, null);
                return resultUri;
            default:
                throw new IllegalArgumentException("Wrong Uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, "query: " + uri.toString());
        dbHelper.getWritableDatabase();
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case URI_INFO :
                cursor = db.query(DBSchemas.Info.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), INFO_URI);
                return cursor;
            case URI_TEMP_SCAN :
                cursor = db.query(DBSchemas.TempScan.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), TEMP_SCAN_URI);
                return cursor;
            case URI_SCANS :
            case URI_SCANS_ID :
                cursor = db.query(DBSchemas.Scans.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), SCANS_URI);
                return cursor;
            case URI_HOSTS :
            case URI_HOSTS_ID :
                cursor = db.query(DBSchemas.Hosts.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), HOSTS_URI);
                return cursor;
            case URI_PORTS :
            case URI_PORTS_ID :
                cursor = db.query(DBSchemas.Ports.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), PORTS_URI);
                return cursor;
            case URI_HOSTNAMES :
            case URI_HOSTNAMES_ID :
                cursor = db.query(DBSchemas.HostNames.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), HOSTNAMES_URI);
                return cursor;
            default:
                throw new IllegalArgumentException("Wrong Uri: " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(LOG_TAG, "update: " + uri.toString());
        dbHelper.getWritableDatabase();
        int cnt;
        String id;
        switch (uriMatcher.match(uri)) {
            case URI_INFO :
                cnt = db.update(DBSchemas.Info.TABLE_NAME, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            case URI_TEMP_SCAN :
                cnt = db.update(DBSchemas.TempScan.TABLE_NAME, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            case URI_SCANS_ID :
                id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "update scan with id: " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = "_id = " + id;
                } else {
                    selection = selection + " AND _id = " + id;
                }
            case URI_SCANS :
                cnt = db.update(DBSchemas.Scans.TABLE_NAME, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            case URI_HOSTS_ID :
                id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "update host with id: " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = "_id = " + id;
                } else {
                    selection = selection + " AND _id = " + id;
                }
            case URI_HOSTS :
                cnt = db.update(DBSchemas.Hosts.TABLE_NAME, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            case URI_PORTS_ID :
                id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "update port with id: " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = "_id = " + id;
                } else {
                    selection = selection + " AND _id = " + id;
                }
            case URI_PORTS :
                cnt = db.update(DBSchemas.Ports.TABLE_NAME, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            case URI_HOSTNAMES_ID :
                id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "update hostname with id: " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = "_id = " + id;
                } else {
                    selection = selection + " AND _id = " + id;
                }
            case URI_HOSTNAMES :
                cnt = db.update(DBSchemas.HostNames.TABLE_NAME, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                return cnt;
            default:
                throw new IllegalArgumentException("Wrong Uri: " + uri);
        }
    }
}
