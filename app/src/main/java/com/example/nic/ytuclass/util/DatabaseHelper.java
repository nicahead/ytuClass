package com.example.nic.ytuclass.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String  DB_NAME = "ytuclass.db";//数据库名称
    private static final int DB_VERSION = 2;//数据库版本号

    public DatabaseHelper(Context context){
         super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table courses(" +
                "id integer primary key autoincrement," +
                "courseName text," +
                "teacher text," +
                "classRoom text," +
                "day integer," +
                "classStart integer," +
                "classEnd integer,"+
                "remark text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
