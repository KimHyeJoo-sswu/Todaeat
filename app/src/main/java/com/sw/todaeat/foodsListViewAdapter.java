package com.sw.todaeat;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class foodsListViewAdapter extends BaseAdapter implements Filterable {
    Context mContext;
    ArrayList<foods> foodsList=new ArrayList<>();
    ArrayList<foods> searchFoodsList=foodsList;

    Filter searchListFilter;

    public foodsListViewAdapter(ArrayList<foods> list,Context context){
        foodsList=list;
        searchFoodsList=foodsList;
        mContext=context;
    }

    @Override
    public int getCount(){
        return searchFoodsList.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public foods getItem(int position){
        return searchFoodsList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos=position;
        final Context context=parent.getContext();

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_foods_list_view_layout,parent,false);
        }

        ImageView foodImage=(ImageView) convertView.findViewById(R.id.foodImage_main);
        TextView foodName=(TextView)convertView.findViewById(R.id.foodName_main);
        TextView time=(TextView)convertView.findViewById(R.id.time_main);
        TextView foodIntroduction=(TextView)convertView.findViewById(R.id.foodIntroduction_main);
        TextView kcal=(TextView)convertView.findViewById(R.id.kcal_main);

        foods food=searchFoodsList.get(position);

        foodImage.setImageResource(food.getFoodImage());
        foodName.setText(food.getFoodName());
        time.setText(String.valueOf(food.getTime())+"분");
        foodIntroduction.setText(food.getIntroduction());
        kcal.setText(String.valueOf(food.getKcal())+"kcal");

        return convertView;
    }

    @Override
    public Filter getFilter(){
        if(searchListFilter==null){
            searchListFilter=new ListFilter();
        }
        return searchListFilter;
    }

    private class ListFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint){
            FilterResults results=new FilterResults();

            if(constraint==null || constraint.length()==0){
                results.values=foodsList;
                results.count=foodsList.size();
            }
            else{
                ArrayList<foods> itemList=new ArrayList<foods>();

                for(foods item:foodsList){
                    if(item.getFoodName().contains(constraint.toString())){
                        itemList.add(item);
                    }
                }
                results.values=itemList;
                results.count=itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results){
            searchFoodsList=(ArrayList<foods>)results.values;
            if(results.count>0){
                notifyDataSetChanged();
            }
            else {
                notifyDataSetInvalidated();
            }
        }
    }
}
