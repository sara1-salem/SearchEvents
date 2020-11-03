package com.example.searchevents;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper{
    public static final String DB_Name = "CCISApp.db";

    public static final String EventTable = "EventData";
    public static final String Title = "TITLE";
    public static final String Type = "TYPE";
    public static final String Time = "TIME";
    public static final String Date = "DATE";
    public static final String Description = "DESCRIPTION";
    public static final String Location = "LOCATION";
    public static final String CoordinatorID = "COORDINATORID";



    public Database( Context context) {
        super(context, "CCISApp.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ EventTable +" ( TITLE TEXT, TYPE TEXT, TIME TIME, DATE DATE, DESCRIPTION TEXT, LOCATION TEXT, COORDINATORID INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+EventTable);
        onCreate(db);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ EventTable,null);
        return res;
    }
    public void searchItem(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor ser=db.rawQuery("SELECT * FROM "+ EventTable,null);

    }
}


