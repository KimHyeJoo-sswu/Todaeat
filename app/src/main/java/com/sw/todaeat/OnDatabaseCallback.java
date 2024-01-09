package com.sw.todaeat;


import java.util.ArrayList;

public interface OnDatabaseCallback {
    public void insert(String name, String date);
    public void update(String oldName, String name, String date);
    public void delete(String name);
    public String search(String name);
    public ArrayList<StuffInfo> selectAll();
}
