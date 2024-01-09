package com.sw.todaeat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter_ingredientCategory extends RecyclerView.Adapter<RecyclerViewAdapter_ingredientCategory.ViewHolder>{
    private Context mContext;
    ArrayList<ingredientsCategory> mIngredientsCategoryList = new ArrayList<ingredientsCategory>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientCategoryName;
        public RecyclerView adapter__ingredient;

        ViewHolder(View itemView) {
            super(itemView);

            ingredientCategoryName = (TextView) itemView.findViewById(R.id.ingredientCategoryName);
            adapter__ingredient = itemView.findViewById(R.id.ingredientCategory);

        }
    }

    public RecyclerViewAdapter_ingredientCategory(ArrayList<ingredientsCategory> arrayList, Context context) {
        this.mIngredientsCategoryList = arrayList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ingredient_category_layout, parent, false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ingredientsCategory item = mIngredientsCategoryList.get(position);
        holder.ingredientCategoryName.setText(item.getIngredientsCategory());

        RecyclerViewAdapter_ingredient childAdapter = new RecyclerViewAdapter_ingredient(item.getIngredients(), mContext);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 5);
        holder.adapter__ingredient.setLayoutManager(gridLayoutManager);

        holder.adapter__ingredient.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return mIngredientsCategoryList.size();
    }
    public void setmIngredientsCategoryList(ArrayList<ingredientsCategory> ingredientsCategories){
        this.mIngredientsCategoryList=ingredientsCategories;
        notifyDataSetChanged();
    }


}