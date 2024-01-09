package com.sw.todaeat;

import android.provider.BaseColumns;

public final class scrapDataBase {
    public static final class CreateDB implements BaseColumns{
        public static final String FOLDERNAME="folderName";
        public static final String FOODIMG="foodImg";
        public static final String FOODNAME="foodName";
        public static final String TIME="time";
        public static final String _TABLENAME0="scraptable";
        public static final String _CREATE0="create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +FOLDERNAME+" text not null , "
                +FOODIMG+" integer not null , "
                +FOODNAME+" text not null , "
                +TIME+" integer not null );";
    }
}
