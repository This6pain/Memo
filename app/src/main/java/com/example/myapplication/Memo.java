package com.example.myapplication;

import java.util.Date;

public class Memo {

    int id;
    String memo;
    String date;

    public Memo(){
        // default
    }
    // create 시에 사용할 생성자
    public Memo(int id, String memo, String date){
        this.id = id;
        this.memo = memo;
        this.date = date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date.toString();
    }


}
