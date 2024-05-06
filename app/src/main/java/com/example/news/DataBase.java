package com.example.news;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper{

    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE if not exists user(name char(20),age int , password char(20) , Email char(20))";
        sqLiteDatabase.execSQL(query);
    }
    public void add(String name , int age , String password , String email)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "INSERT INTO user values(?,?,?,?)";
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("age",age);
        cv.put("password",password);
        cv.put("Email",email);
        sqLiteDatabase.insert("user",null,cv);
        sqLiteDatabase.close();
    }

    public boolean check(String username , String password)
    {
        String query = "SELECT count(*) from user where name = ? and password = ?";
        SQLiteDatabase sql = getReadableDatabase();
        try(Cursor c = sql.rawQuery(query,new String[]{username,password}))
        {
            if(c.moveToFirst())
            {
                return c.getInt(0)>0;
            }
        }
        catch(Exception e)
        {
            e.fillInStackTrace();
        }
        finally {
            sql.close();
        }
            return false;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
