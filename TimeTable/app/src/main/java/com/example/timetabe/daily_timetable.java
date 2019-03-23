package com.example.timetabe;

public class daily_timetable {
    String value,n;

    public daily_timetable() {

    }


    public daily_timetable(String value,String n) {
        this.value = value;
        this.n=n;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }
}
