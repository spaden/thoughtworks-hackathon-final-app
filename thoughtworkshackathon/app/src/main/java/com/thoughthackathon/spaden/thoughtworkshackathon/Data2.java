package com.thoughthackathon.spaden.thoughtworkshackathon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

public class Data2 {
    private SQLiteDatabase db;
    private static final String DB_NAME = "kal_db";
    private static final int DB_VERSION = 1;
    private final String TABLE_NAME3="scart";

    public Data2(Context context) {
        // Create an instance of our internal CustomSQLiteOpenHelper class
        Data2.CustomSQLiteOpenHelper helper = new Data2.CustomSQLiteOpenHelper(context);

        // Get a writable database
        db = helper.getWritableDatabase();
    }
    public Cursor select3(){
        Cursor c=db.rawQuery("SELECT NAME,STYLE,ABV,IBU,UID,OUNCE FROM "+TABLE_NAME3,null);
        return c;
    }
    public  void delete2(){
        db.execSQL("DELETE  FROM "+TABLE_NAME3);
    }
    public  void insert3(List<beerobj> yo) {
        ContentValues contentValues = new ContentValues();
        String name, ibu, abv, style;
        int uid;
        double ounce, abv1, ibu1;
     try{
         for (int i = 0; i < yo.size(); i++) {
             beerobj myobj = new beerobj();
             myobj = yo.get(i);
             name = myobj.getName();
             style = myobj.getStyle();
             uid = myobj.getId();
             ounce = myobj.getOunces();
             contentValues.put("NAME", name);
             contentValues.put("STYLE", style);
             if (myobj.getIbv() == null) {
                 contentValues.put("IBU", 0);
             } else {
                 contentValues.put("IBU", myobj.getIbv());
             }

             contentValues.put("UID", uid);
             contentValues.put("OUNCE", ounce);
             if (myobj.getAbv() == null) {
                 contentValues.put("ABV", 0);
             } else {
                 contentValues.put("ABV", myobj.getAbv());

             }
             long result = db.insert(TABLE_NAME3, null, contentValues);
             if (result == -1)
                 Log.i("failed", "failed");
             else
                 Log.i("success", "done");
         }
     }catch (SQLException e){

     }

    }










    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        // This method only runs the first time the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {

            // Create a table for photos and all their details
            db.execSQL("create table " + TABLE_NAME3 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,NAME TEXT,STYLE TEXT,ABV DOUBLE,IBU INTEGER,UID INTEGER,OUNCE DOUBLE)");

        }

        // This method only runs when we increment DB_VERSION
        // We will look at this in chapter 26
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }


}
