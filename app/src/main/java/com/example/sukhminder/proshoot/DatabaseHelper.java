package com.example.sukhminder.proshoot;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void querydata(String sql_){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql_);
    }
    public void insertData(String name,byte[] image){//database insertion
SQLiteDatabase database=getWritableDatabase();
String sql="INSERT INTO GALLERY VALUES(NULL,?,?)";
SQLiteStatement query=database.compileStatement(sql);
query.clearBindings();
query.bindString(1,name);
query.bindBlob(2,image);
        query.executeInsert();//
    }
    public Cursor getinfo(String sql){
SQLiteDatabase database=getReadableDatabase();
return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
