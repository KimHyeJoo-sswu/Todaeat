package com.sw.todaeat;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class StuffDatabase {
    public static final String TAG = "StuffDatabase";
    private static StuffDatabase database;	//싱글톤을 위해 private static 으로 선언.
    public static String DATABASE_NAME = "stuff.db";
    public static String TABLE_STUFF_INFO = "STUFF_INFO";
    public static int DATABASE_VERSION = 1;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    //왜 context를 넘겨주나??
    //context란 시스템이 관리하고 있는 전역적인 정보에 접근하고 API를 호출하기 위해 .... ??
    //안드로이드 운영체제에서는 어플리케이션이 여타 플랫폼과는 달리 관리되므로 단순하게 시스템 API를 호출하기 위해서는 context 가 필요.
    private StuffDatabase(Context context) {
        this.context = context;
    }

    //싱글톤 : 생성자를 private 으로 정의해 다른 클래스에서 해당 클래스의 인스턴스 new로 생성하지 못하게 막고 getInstance() 함수를 통해서만 생성하게 함.
    //해당 인스턴스는 단 한번만 static 메모리 영역에 할당하여 생성자 여러번 호출하더라도 최초에 생성된 인스턴스를 반환.
    public static StuffDatabase getInstance(Context context) {
        if (database == null) {
            //만약 생성된 적이 없다면 최초로 한번 만들어라...
            database = new StuffDatabase(context);
        }
        //만들었었다면 최초에 생성되었던 인스턴스를 반환해라...
        return database;
    }

    public boolean open() {
        println("opening database [" + DATABASE_NAME + "].");

        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

        return true;
    }

    public void close() {
        println("closing database [" + DATABASE_NAME + "].");
        db.close();
        database = null;
    }

    public Cursor rawQuery(String SQL) {
        println("\nexecuteQuery called.\n");

        Cursor c1 = null;
        try {
            c1 = db.rawQuery(SQL, null);
            println("cursor count : " + c1.getCount());
        } catch(Exception ex) {
            Log.e(TAG, "Exception in executeQuery", ex);
        }
        return c1;
    }

    public boolean execSQL(String SQL) {
        println("\nexecute called.\n");

        try {
            Log.d(TAG, "SQL : " + SQL);
            db.execSQL(SQL);
        } catch(Exception ex) {
            Log.e(TAG, "Exception in executeQuery", ex);
            return false;
        }
        return true;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase _db) {
            // TABLE_STUFF_INFO
            println("creating table [" + TABLE_STUFF_INFO + "].");

            // drop existing table
            String DROP_SQL = "drop table if exists " + TABLE_STUFF_INFO;
            try {
                _db.execSQL(DROP_SQL);
            } catch(Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }
            // create table
            String CREATE_SQL = "create table " + TABLE_STUFF_INFO + "("
                    + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  NAME TEXT not null, "
                    + "  DATE TEXT not null, "
                    + "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";
            try {
                _db.execSQL(CREATE_SQL);
            } catch(Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }
        }

        public void onOpen(SQLiteDatabase db) {
            println("opened database [" + DATABASE_NAME + "].");

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            println("Upgrading database from version " + oldVersion + " to " + newVersion + ".");

            if (oldVersion < 2) {   // version 1

            }

        }
        private void insertRecord(SQLiteDatabase _db, String name, String date) {
            try {
                _db.execSQL( "insert into " + TABLE_STUFF_INFO + "(NAME, DATE) values ('" + name + "', '" + date+ "');" );
            } catch(Exception ex) {
                Log.e(TAG, "Exception in executing insert SQL.", ex);
            }
        }

    }


    public String searchRecord(String name) {
        String result = "";
        try{
            Cursor cursor = db.rawQuery("select NAME from " + TABLE_STUFF_INFO + " where NAME = ?", new String[]{name});
            cursor.moveToNext();
            result = cursor.getString(0);
        } catch (Exception ex) {
            Log.e(TAG, "Exception in executing search SQL.", ex);
        }
        return result;
    }

    public void deleteRecord(String name) {
        try {
            db.execSQL("delete from " + TABLE_STUFF_INFO + " where NAME = '" + name + "';");
        } catch(Exception ex) {
            Log.e(TAG, "Exception in executing delete SQL.", ex);
        }
    }

    public void updateRecord(String oldName, String name, String date) {
        try{
            db.execSQL("update " + TABLE_STUFF_INFO + " set NAME = '" + name + "', DATE = '" + date + "' where NAME = '" + oldName + "';" );
        } catch (Exception ex) {
            Log.e(TAG, "Exception in executing update SQL.", ex);
        }
    }

    public void insertRecord(String name, String date) {
        try {
            db.execSQL( "insert into " + TABLE_STUFF_INFO + "(NAME, DATE) values ('" + name + "', '" + date + "');" );
        } catch(Exception ex) {
            Log.e(TAG, "Exception in executing insert SQL.", ex);
        }
    }

    public ArrayList<StuffInfo> selectAll() {
        ArrayList<StuffInfo> result = new ArrayList<StuffInfo>();

        try {
            Cursor cursor = db.rawQuery("select NAME, DATE from " + TABLE_STUFF_INFO, null);
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String name = cursor.getString(0);
                String date = cursor.getString(1);

                StuffInfo info = new StuffInfo(name, date);
                result.add(info);
            }

        } catch(Exception ex) {
            Log.e(TAG, "Exception in executing insert SQL.", ex);
        }

        return result;
    }

    private void println(String msg) {
        Log.d(TAG, msg);
    }
}