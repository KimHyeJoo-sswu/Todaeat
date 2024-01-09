package com.sw.todaeat;

public class scrapFoods {
    private int foodImage;
    private String foodName;
    private int time;

    scrapFoods(int foodImage, String foodName, int time){
        this.foodImage=foodImage;
        this.foodName=foodName;
        this.time=time;
    }

    public int getFoodImage(){
        return foodImage;
    }

    public String getFoodName(){
        return foodName;
    }

    public int getTime(){
        return time;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
