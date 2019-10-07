package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.sql.Date;

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

    public void updateMemo(Memo memo){
        SQLiteDatabase db = getWritableDatabase();
        String content = memo.getMemo();
        db.execSQL("UPDATE MEMO SET memo = '"+ content + "', date = CURRENT_TIMESTAMP WHERE -id = "+ memo.getId() +");");
        db.close();
    }

    public void deleteMemo(int no){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM MEMO WHERE _id = '" + no + "';");
        db.close();
    }

    public ArrayList<Memo> getMemos(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM MEMO";
        ArrayList<Memo> memoList = new ArrayList<Memo>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String content = cursor.getString(1);
                String date = cursor.getString(2);
                memoList.add(new Memo(id, content, date));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return memoList;
    }
}
