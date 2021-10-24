package com.example.flashclock.Model;

//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//@Entity(tableName = "time")

public class ListTime {

    private int id;

    public ListTime(String strTime, String strNotice) { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String notice;
    private String time;

    public ListTime(String time) {
        this.time = time;
        this.notice = notice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public  String getNotice(){ return  notice;}
    public  void  setNotice(String notice) {this.notice = notice;}

    public ListTime() {
    }
}
