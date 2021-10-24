package com.example.flashclock.Model;

import java.io.Serializable;

public class TimeNation implements Serializable {
    private String GTM;
    private String Location;
    private String Time;

    public TimeNation() {
    }

    public TimeNation(String GTM, String location, String time) {
        this.GTM = GTM;
        Location = location;
        Time = time;
    }

    public String getGTM() {
        return GTM;
    }

    public void setGTM(String GTM) {
        this.GTM = GTM;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "TimeNation{" +
                "GTM='" + GTM + '\'' +
                ", Location='" + Location + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
