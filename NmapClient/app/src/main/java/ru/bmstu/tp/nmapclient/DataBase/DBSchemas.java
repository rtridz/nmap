package ru.bmstu.tp.nmapclient.DataBase;

import android.provider.BaseColumns;

public final class DBSchemas {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NmapClient";

    public DBSchemas() {}

    // ------------- info -----------------

    public static final String SQL_CREATE_TABLE_INFO =
            "CREATE TABLE " + Info.TABLE_NAME + " (" +
                    Info._ID + " INTEGER PRIMARY KEY," +
                    Info.USER_ID + " INTEGER," +
//                    Info.GCM_REG_ID + " TEXT," +
                    ")";

    public static final String SQL_DELETE_INFO =
            "DROP TABLE IF EXISTS " + Info.TABLE_NAME;

    public static abstract class Info implements BaseColumns {
        public static final String TABLE_NAME = "info";

        public static final String USER_ID = "user_id";
//        public static final String GCM_REG_ID = "reg_id";
    }

    // ------------- temp_scan -----------------

    public static final String SQL_CREATE_TABLE_TEMP_SCAN =
            "CREATE TABLE " + TempScan.TABLE_NAME + " (" +
                    TempScan._ID + " INTEGER PRIMARY KEY," +
                    TempScan.TIME + " DATETIME," +
                    TempScan.COMMAND + " TEXT," +
                    ")";

    public static final String SQL_DELETE_TEMP_SCAN =
            "DROP TABLE IF EXISTS " + TempScan.TABLE_NAME;

    public static abstract class TempScan implements BaseColumns {
        public static final String TABLE_NAME = "temp_scan";

        public static final String TIME = "time";
        public static final String COMMAND = "command";
    }

    // ------------- scans -----------------

    public static final String SQL_CREATE_TABLE_SCANS =
            "CREATE TABLE " + Scans.TABLE_NAME + " (" +
                    Scans._ID + " INTEGER PRIMARY KEY," +
                    Scans.TIME + " DATETIME," +
                    Scans.COMMAND + " TEXT," +
                    ")";

    public static final String SQL_DELETE_SCANS =
            "DROP TABLE IF EXISTS " + Scans.TABLE_NAME;

    public static abstract class Scans implements BaseColumns {
        public static final String TABLE_NAME = "scans";

        public static final String TIME = "time";
        public static final String COMMAND = "command";
    }

    // ------------- hosts -----------------

    public static final String SQL_CREATE_TABLE_HOSTS =
            "CREATE TABLE " + Hosts.TABLE_NAME + " (" +
                    Hosts._ID + " INTEGER PRIMARY KEY," +
                    Hosts.SCAN_ID + " INTEGER," +
                    Hosts.TIME + " DATETIME," +
                    Hosts.IP_ADR + " VARCHAR(50)," +
                    Hosts.IP_VER + " VARCHAR(5)," +
                    Hosts.OS_INFO + " TEXT," +
                    Hosts.TRACE + " TEXT," +
                    Hosts.ANY_INFO + " TEXT," +
                    ")";

    public static final String SQL_DELETE_HOSTS =
            "DROP TABLE IF EXISTS " + Hosts.TABLE_NAME;

    public static abstract class Hosts implements BaseColumns {
        public static final String TABLE_NAME = "hosts";

        public static final String SCAN_ID = "scan_id";
        public static final String TIME = "time";
        public static final String IP_ADR = "ip";
        public static final String IP_VER = "ip_ver";
        public static final String OS_INFO = "os_info";
        public static final String TRACE = "trace_route";
        public static final String ANY_INFO = "any_info";
    }

    // ------------- ports -----------------

    public static final String SQL_CREATE_TABLE_PORTS =
            "CREATE TABLE " + Ports.TABLE_NAME + " (" +
                    Ports._ID + " INTEGER PRIMARY KEY," +
                    Ports.HOST_ID + " INTEGER," +
                    Ports.PORT_ID + " INTEGER," +
                    Ports.PROTOCOL + " TEXT," + // может быть не только TCP/UDP
                    Ports.ANY_INFO + " TEXT," +
                    ")";

    public static final String SQL_DELETE_PORTS =
            "DROP TABLE IF EXISTS " + Ports.TABLE_NAME;

    public static abstract class Ports implements BaseColumns {
        public static final String TABLE_NAME = "ports";

        public static final String HOST_ID = "host_id";
        public static final String PORT_ID = "number";
        public static final String PROTOCOL = "protocol";
        public static final String ANY_INFO = "any_info";
    }

    // ------------- host_names -----------------

    public static final String SQL_CREATE_TABLE_HOSTNAMES =
            "CREATE TABLE " + HostNames.TABLE_NAME + " (" +
                    HostNames._ID + " INTEGER PRIMARY KEY," +
                    HostNames.HOST_ID + " INTEGER," +
                    HostNames.NAME + " VARCHAR(100)," +
                    HostNames.TYPE + " VARCHAR(50)," +
                    ")";

    public static final String SQL_DELETE_HOSTNAMES =
            "DROP TABLE IF EXISTS " + HostNames.TABLE_NAME;

    public static abstract class HostNames implements BaseColumns {
        public static final String TABLE_NAME = "host_names";

        public static final String HOST_ID = "host_id";
        public static final String NAME = "name";
        public static final String TYPE = "type";
    }
}
