package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MEMO (_id INTEGER PRIMARY KEY AUTOINCREMENT, memo TEXT, date DATETIME DEFAULT CURRENT_TIMESTAMP);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addMemo(Memo memo){
        SQLiteDatabase db = getWritableDatabase();
        String content = memo.getMemo();
        db.execSQL("INSERT INTO MEMO(memo) VALUES('" + content + "');");
        db.close();
    }
}
