package com.thoughthackathon.spaden.thoughtworkshackathon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

public class DataManager {
    private final String TABLE_NAME1 = "searchres";
    private final String TABLE_NAME2="cartme";
    private SQLiteDatabase db;
    private static final String DB_NAME = "users_db";
    private static final int DB_VERSION = 1;

    public DataManager(Context context) {
        // Create an instance of our internal CustomSQLiteOpenHelper class
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);

        // Get a writable database
        db = helper.getWritableDatabase();
    }

    public void insert1(List<beerobj> yo){
        ContentValues contentValues = new ContentValues();
        String name,ibu,abv,style;
        int uid;
        double ounce,abv1,ibu1;
        for(int i=0;i<yo.size();i++){
              beerobj myobj=new beerobj();
              myobj=yo.get(i);
              name=myobj.getName();
              style=myobj.getStyle();
              uid=myobj.getId();
              ounce=myobj.getOunces();

            contentValues.put("NAME",name);
            contentValues.put("STYLE",style);
            if(myobj.getIbv() == null){
                contentValues.put("IBU",0);
            }else {
                contentValues.put("IBU",myobj.getIbv());
            }

            contentValues.put("UID",uid);
             contentValues.put("OUNCE",ounce);
            if(myobj.getAbv() == null){
                contentValues.put("ABV",0);
            }else {
                contentValues.put("ABV",myobj.getAbv());

            }
            long result = db.insert(TABLE_NAME1,null ,contentValues);
            if(result == -1)
                Log.i("failed","failed");
            else
                Log.i("success","done");
        }
    }
    public void inserttwo(beerobj obj){
        ContentValues ct=new ContentValues();
        ct.put("NAME",obj.getName());
        ct.put("STYLE",obj.getStyle());
        ct.put("OUNCE",obj.getOunces());
        long result = db.insert(TABLE_NAME2,null ,ct);

    }
     public  Cursor selectDistinct(){
        Cursor  c=db.rawQuery("SELECT DISTINCT STYLE FROM "+TABLE_NAME1,null);
        return c;
     }
     public Cursor selectasc(){
        Cursor c=db.rawQuery("SELECT NAME,STYLE,ABV,IBU,UID,OUNCE" +" from " +
                TABLE_NAME1+   "  ORDER BY " +
                "ABV ASC",null);
        return  c;
     }
     public  Cursor selectdesc(){
         Cursor c=db.rawQuery("SELECT NAME,STYLE,ABV,IBU,UID,OUNCE" +" from " +
                 TABLE_NAME1+   "  ORDER BY " +
                 "ABV DESC",null);
         return  c;
     }
    public  void insert2(List<beerobj> yo){
        ContentValues contentValues = new ContentValues();
        String name,ibu,abv,style;
        double ounce,abv1,ibu1;
        for(int i=0;i<yo.size();i++){
            beerobj myobj=new beerobj();
            myobj=yo.get(i);
            name=myobj.getName();
            style=myobj.getStyle();
            ounce=myobj.getOunces();
            abv1=Double.parseDouble(myobj.getAbv());
            ibu1=Double.parseDouble(myobj.getIbv());
            contentValues.put("NAME",name);
            if(myobj.getIbv() == null){
                contentValues.put("IBU",0);
            }else {
                contentValues.put("IBU",ibu1);

            }
            contentValues.put("OUNCE",ounce);
            contentValues.put("ABV",abv1);
            //contentValues.put("UID",myobj.getId());
            long result = db.insert(TABLE_NAME2,null ,contentValues);
            if(result == -1)
                Log.i("failed","failed");
            else
                Log.i("success","done");
        }
    }
    public void close(){
         db.close();
    }

    public Cursor select1(String name) {
        Cursor c = db.rawQuery("SELECT NAME,STYLE,ABV,IBU,UID,OUNCE" +" from " +
                TABLE_NAME1+" WHERE NAME" +" = "+ "'"+name+"'" +"OR STYLE "+" = "+ "'"+name+"'", null);

        return c;
    }
    public void delete(){

        // Delete the details from the table if already exists
        String yo="ID";

        db.execSQL("DELETE  FROM "+TABLE_NAME1);

    }

    public Cursor isFUll1(){
              Cursor c=db.rawQuery("SELECT * FROM "+TABLE_NAME1,null);
              return c;
    }
    public Cursor select2(){

            Cursor c = db.rawQuery("SELECT NAME,STYLE,OUNCE " +" from " +
                    TABLE_NAME2, null);

            return c;
        }
    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        private final String TABLE_NAME1 = "searchres";
        private final String TABLE_NAME2="cartme";
        private static final String DB_NAME = "users_db";
        private static final int DB_VERSION = 1;

        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        // This method only runs the first time the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {

            // Create a table for photos and all their details
            String newTableQueryString = "create table " + TABLE_NAME1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,NAME TEXT,STYLE TEXT,IBU INTEGER,UID INTEGER,OUNCE DOUBLE)";
            db.execSQL("create table " + TABLE_NAME1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,NAME TEXT,STYLE TEXT,ABV DOUBLE,IBU INTEGER,UID INTEGER,OUNCE DOUBLE)");
            db.execSQL("create table "+TABLE_NAME2+" (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,NAME TEXT,STYLE TEXT,OUNCE DOUBLE)");

        }

        // This method only runs when we increment DB_VERSION
        // We will look at this in chapter 26
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
}





















