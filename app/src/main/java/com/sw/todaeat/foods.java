package com.sw.todaeat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class foods {
    String foodName;
    String introduction;
    int time;
    int kcal;
    int foodImage;
    String[] list;

    public foods(int foodImage, String foodName, String introduction, int time, int kcal, String[] list){
        this.foodImage=foodImage;
        this.foodName=foodName;
        this.introduction=introduction;
        this.time=time;
        this.kcal=kcal;
        this.list=list;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public String getFoodName(){
        return foodName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getTime() {
        return time;
    }

    public int getKcal() {
        return kcal;
    }

    public String[] getList() {
        return list;
    }
}
