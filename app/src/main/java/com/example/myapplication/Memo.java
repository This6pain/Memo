package com.example.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Memo {

    private int id;
    private String memo;
    private String date;

    public Memo(){
    }

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

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");
        Date dateTime;
        String newDate = "";
        try {
            dateTime = format.parse(date);
            newDate = outputFormat.format(dateTime);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return newDate;
    }

    public void setDate(Date date) {
        this.date = date.toString();
    }


}
