package com.sw.todaeat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class scrapDBOpenHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(scrapDataBase.CreateDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+scrapDataBase.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public scrapDBOpenHelper(Context context){
        this.mCtx = context;
    }

    public scrapDBOpenHelper open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
        deleteAllColumns();
        for(scrapFoodListByFolderName item: scrapPage.scrapped){
            insertColumn(item.getFolderName().getFolderName(),item.getScrapFood().getFoodImage(),item.getScrapFood().getFoodName(), item.getScrapFood().getTime());
        }
    }

    public void close(){
        mDB.close();
    }

    // Insert DB
    public long insertColumn(String folderName, long foodImage, String foodName, long time){
        ContentValues values = new ContentValues();
        values.put(scrapDataBase.CreateDB.FOLDERNAME, folderName);
        values.put(scrapDataBase.CreateDB.FOODIMG, foodImage);
        values.put(scrapDataBase.CreateDB.FOODNAME, foodName);
        values.put(scrapDataBase.CreateDB.TIME, time);
        return mDB.insert(scrapDataBase.CreateDB._TABLENAME0, null, values);
    }

    // Update DB
    public boolean updateColumn(long id, String folderName, long foodImg, String foodName , long time){
        ContentValues values = new ContentValues();
        values.put(scrapDataBase.CreateDB.FOLDERNAME, folderName);
        values.put(scrapDataBase.CreateDB.FOODIMG, foodImg);
        values.put(scrapDataBase.CreateDB.FOODNAME, foodName);
        values.put(scrapDataBase.CreateDB.TIME, time);
        return mDB.update(scrapDataBase.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
    }

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(scrapDataBase.CreateDB._TABLENAME0, null, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(scrapDataBase.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }
    // Select DB
    public Cursor selectColumns(){
        return mDB.query(scrapDataBase.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }

    // sort by column
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }

 }
