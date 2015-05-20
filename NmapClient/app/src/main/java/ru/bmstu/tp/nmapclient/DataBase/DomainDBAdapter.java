package ru.bmstu.tp.nmapclient.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


// исключительно для демонстрации работы HistorylistActivity
public class DomainDBAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String DOMAIN = "domain";
    public static final String KEY_FROM_PORT = "portFrom";
    public static final String KEY_TO_PORT = "region";

    private static final String TAG = "DomainDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "Contries";
    private static final String SQLITE_TABLE = "Domain";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    DOMAIN + "," +
                    KEY_FROM_PORT + "," +
                    KEY_TO_PORT + "," +
                    " UNIQUE (" + DOMAIN +"));";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public DomainDBAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public DomainDBAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createCountry(String code,
                              String fromPort, String toPort) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(DOMAIN, code);
        initialValues.put(KEY_FROM_PORT, fromPort);
        initialValues.put(KEY_TO_PORT, toPort);

        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllDomains() {

        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    public Cursor fetchDomainByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                            DOMAIN, KEY_FROM_PORT, KEY_TO_PORT},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
                            DOMAIN, KEY_FROM_PORT, KEY_TO_PORT},
                    DOMAIN + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllDomains() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                        DOMAIN, KEY_FROM_PORT, KEY_TO_PORT},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertSomeCountries() {

        createCountry("main.ru","10","80");
        createCountry("ya.ru","1","65535");
        createCountry("vk.com","2","120");
        createCountry("amazon.com","20","22");
        createCountry("russia.com","1","4");
        createCountry("android.su","80","700");
        createCountry("vata.ku","42","71");
        createCountry("bmstu.com","2","120");
        createCountry("krasnodar.com","20","22");
        createCountry("loris.com","1","4");
        createCountry("izmailovo.su","80","700");
    }
}
