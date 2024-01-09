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

public class scrapListViewAdapter extends BaseAdapter implements Filterable {
    Context mContext;
    ArrayList<scrapFoodListByFolderName> scrapFoodList_folderName=new ArrayList<scrapFoodListByFolderName>();
    ArrayList<scrapFoodListByFolderName> searchscrapFoodList=scrapFoodList_folderName;

    Filter searchListFilter;

    public scrapListViewAdapter(Context context){
        mContext=context;
    }

    @Override
    public int getCount(){
        return searchscrapFoodList.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public scrapFoodListByFolderName getItem(int position){
        return searchscrapFoodList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos=position;
        final Context context=parent.getContext();

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_scrap_food_list_view_layout,parent,false);
        }

        ImageView foodImage=(ImageView) convertView.findViewById(R.id.foodImage);
        TextView foodName=(TextView)convertView.findViewById(R.id.foodName);
        TextView time=(TextView)convertView.findViewById(R.id.time);
        Button deleteScrap=(Button)convertView.findViewById(R.id.deleteScrap);

        scrapFoodListByFolderName scrap_foods=searchscrapFoodList.get(position);

        foodImage.setImageResource(scrap_foods.getScrapFood().getFoodImage());
        foodName.setText(scrap_foods.getScrapFood().getFoodName());
        time.setText(String.valueOf(scrap_foods.getScrapFood().getTime())+"분");

        deleteScrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor iCursor=scrapPage.mDBOpenHelper.selectColumns();
                int i=0;
                while(iCursor.moveToNext()){
                    String tempFolderName=iCursor.getString(iCursor.getColumnIndex("folderName"));
                    int tempFoodImg=iCursor.getInt(iCursor.getColumnIndex("foodImg"));
                    String tempFoodName=iCursor.getString(iCursor.getColumnIndex("foodName"));
                    int tempTime=iCursor.getInt(iCursor.getColumnIndex("time"));

                    scrapfolderNames tempfoldername=new scrapfolderNames(tempFolderName);
                    scrapFoods tempscrapfood=new scrapFoods(tempFoodImg,tempFoodName,tempTime);

                    scrapFoodListByFolderName tempScrapItem=new scrapFoodListByFolderName(tempfoldername,tempscrapfood);;

                    scrapFoodListByFolderName originalScrapItem=scrapFoodList_folderName.get(position);
                    if(tempScrapItem.getScrapFood().getFoodName().equals(originalScrapItem.scrapFood.getFoodName())) {
                        scrapFoodList_folderName.remove(position);
                        scrapPage.mDBOpenHelper.deleteColumn(i);
                        ArrayList<scrapFoodListByFolderName> temp=scrapPage.scrapped;
                        for(scrapFoodListByFolderName item : scrapPage.scrapped){
                            if(item.getScrapFood().getFoodName().equals(originalScrapItem.scrapFood.getFoodName())){
                                temp.remove(item);
                                break;
                            }
                        }
                        scrapPage.scrapped=temp;
                        scrapPage.scrapped.remove(originalScrapItem);
                        notifyDataSetChanged();
                        break;
                    }
                    i++;
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public void addItem(String folderName, int newFoodImage, String newScrapFoodName, int newTime){
        scrapFoods scrap_food = new scrapFoods(newFoodImage, newScrapFoodName, newTime);

        scrapfolderNames folderNameToAdd=new scrapfolderNames(folderName);

        scrapFoodListByFolderName newFolder = new scrapFoodListByFolderName(folderNameToAdd,scrap_food);

        boolean exist=false;
        for(scrapFoodListByFolderName item : scrapFoodList_folderName){
            if(item==newFolder){
                exist=true;
            }
        }
        if(exist==false) {
            scrapFoodList_folderName.add(newFolder);
            scrapPage.mDBOpenHelper.insertColumn(folderName, newFoodImage, newScrapFoodName,newTime);
        }
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
                results.values=scrapFoodList_folderName;
                results.count=scrapFoodList_folderName.size();
            }
            else{
                ArrayList<scrapFoodListByFolderName> itemList=new ArrayList<scrapFoodListByFolderName>();

                for(scrapFoodListByFolderName item:scrapFoodList_folderName){
                    if(item.getScrapFood().getFoodName().contains(constraint.toString())){
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
            searchscrapFoodList=(ArrayList<scrapFoodListByFolderName>)results.values;
            if(results.count>0){
                notifyDataSetChanged();
            }
            else {
                notifyDataSetInvalidated();
            }
        }
    }

    public void clear(){
        scrapFoodList_folderName.clear();
        scrapPage.mDBOpenHelper.deleteAllColumns();
    }

}
