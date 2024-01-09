package com.sw.todaeat;

import java.util.ArrayList;

public class ingredientsCategory {
    String categoryName;
    ArrayList<ingredients> ingredients;

    public ingredientsCategory(String categoryName, ArrayList<ingredients> ingredients){
        this.ingredients=ingredients;
        this.categoryName=categoryName;
    }

    public ArrayList<ingredients> getIngredients(){
        return ingredients;
    }

    public String getIngredientsCategory(){
        return categoryName;
    }

    public void setIngredients(ArrayList<ingredients> ingredients){
        this.ingredients=ingredients;
    }

    public void setCategoryName(String categoryName){
        this.categoryName=categoryName;
    }
}
