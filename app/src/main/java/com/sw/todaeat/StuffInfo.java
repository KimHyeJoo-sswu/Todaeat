package com.sw.todaeat;


public class StuffInfo {
    String name;
    String date;

    public StuffInfo(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}