package com.sw.todaeat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter_ingredient extends RecyclerView.Adapter<RecyclerViewAdapter_ingredient.ViewHolder> implements Filterable {
    private Context mContext;
    private MainActivity activity;
    ArrayList<ingredients> mIngredientList=new ArrayList<>();
    ArrayList<ingredients> filteredList=mIngredientList;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ingredientIcon;
        TextView ingredientName;

        ViewHolder(View itemView) {
            super(itemView);
            ingredientIcon = (ImageView) itemView.findViewById(R.id.ingredientIcon);
            ingredientName = (TextView) itemView.findViewById(R.id.ingredientName);

            ingredientIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    ingredients item = mIngredientList.get(pos);

                    item.changeSelected();
                    if (item.selected == false) {
                        MainActivity.fridge.remove(item);
                        item.setIngredientShowing(item.getIngredientIcon());
                    } else {
                        MainActivity.fridge.add(item);
                        item.setIngredientShowing(item.check);
                    };
                    notifyDataSetChanged();

                }
            });

        }
    }

    public RecyclerViewAdapter_ingredient(ArrayList<ingredients> arrayList, Context context) {
        this.mContext = context;
        this.mIngredientList = arrayList;
        this.filteredList=mIngredientList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ingredient_layout, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ingredients item = filteredList.get(position);

        holder.ingredientIcon.setBackgroundResource(item.getIngredientShowing());
        holder.ingredientName.setText(item.getIngredientName());
    }

    @Override
    public int getItemCount() {
        return mIngredientList.size();
    }

    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString=constraint.toString();
                if(charString.isEmpty()){
                    filteredList=mIngredientList;
                }
                else{
                    ArrayList<ingredients> filteringList=new ArrayList<>();
                    for(ingredients item : mIngredientList){
                        if(item.getIngredientName().contains(charString)){
                            filteringList.add(item);
                        }
                    }
                    filteredList=filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values=filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList=(ArrayList<ingredients>)results.values;
                notifyDataSetChanged();
            }
        };
    }

}