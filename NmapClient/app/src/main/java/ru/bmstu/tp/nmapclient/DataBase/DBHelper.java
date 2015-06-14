package ru.bmstu.tp.nmapclient.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, DBSchemas.DATABASE_NAME, null, DBSchemas.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBSchemas.SQL_CREATE_TABLE_INFO);
        db.execSQL(DBSchemas.SQL_CREATE_TABLE_TEMP_SCAN);
        db.execSQL(DBSchemas.SQL_CREATE_TABLE_SCANS);
        db.execSQL(DBSchemas.SQL_CREATE_TABLE_HOSTS);
        db.execSQL(DBSchemas.SQL_CREATE_TABLE_PORTS);
        db.execSQL(DBSchemas.SQL_CREATE_TABLE_HOSTNAMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBSchemas.SQL_DELETE_INFO);
        db.execSQL(DBSchemas.SQL_DELETE_TEMP_SCAN);
        db.execSQL(DBSchemas.SQL_DELETE_SCANS);
        db.execSQL(DBSchemas.SQL_DELETE_HOSTS);
        db.execSQL(DBSchemas.SQL_DELETE_PORTS);
        db.execSQL(DBSchemas.SQL_DELETE_HOSTNAMES);
        onCreate(db);
    }
}
