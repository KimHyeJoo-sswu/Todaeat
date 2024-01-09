package com.sw.todaeat;

public class scrapFoodListByFolderName {
    scrapfolderNames folderName;
    scrapFoods scrapFood;

    scrapFoodListByFolderName(scrapfolderNames folderName, scrapFoods scrapFood){
        this.folderName=folderName;
        this.scrapFood=scrapFood;
    }

    public scrapfolderNames getFolderName(){
        return folderName;
    }

    public scrapFoods getScrapFood(){
        return scrapFood;
    }

    public void setFolderName(scrapfolderNames folderName){
        this.folderName=folderName;
    }

    public void setScrapFood(scrapFoods scrapFood){
        this.scrapFood=scrapFood;
    }
}
