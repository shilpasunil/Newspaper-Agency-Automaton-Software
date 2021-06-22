package com.example.logindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

public class DBHelperAddArticle extends SQLiteOpenHelper {

    public DBHelperAddArticle(Context context) {

        super(context, "article.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(name TEXT primary key, type TEXT, cost INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String name, String type, int cost) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("cost", cost);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from users",null);
        return cursor;
    }
    public Boolean checkarticle(String name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where name = ?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Cursor getCost(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select cost from users where name = ?", new String[]{name});
        return cursor;
    }


}