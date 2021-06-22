package com.example.logindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBart extends SQLiteOpenHelper {

    public DBart(Context context) {

        super(context, "artsub.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT, article TEXT PRIMARY KEY, cost INTEGER )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }


    public Boolean insertArticle(String username, String article, int cost){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("article", article);
        contentValues.put("cost", cost);

        long result = MyDB.insert("users", null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }

    }





    public Boolean delete(String username, String article){
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("users","username=? and article=?",new String[]{username,article});
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkarticle(String name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where article = ?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from users",null);
        return cursor;
    }


}