package com.sw.todaeat;

public class ingredients {
    String ingredientName;
    int ingredientIcon;
    int check;
    int ingredientShowing;
    boolean selected=false;

    public ingredients(String ingredientName, int ingredientIcon, int check){
        this.ingredientName=ingredientName;
        this.ingredientIcon=ingredientIcon;
        this.check=check;
        this.ingredientShowing=ingredientIcon;
    }

    public int getIngredientIcon(){
        return ingredientIcon;
    }

    public String getIngredientName(){
        return ingredientName;
    }

    public int getIngredientShowing(){
        return ingredientShowing;
    }

    public void setIngredientShowing(int ingredientIcon){
        this.ingredientShowing=ingredientIcon;
    }

    public void changeSelected(){
        if(selected==false){
            selected=true;
        }
        else{
            selected=false;
        }
    }
}
