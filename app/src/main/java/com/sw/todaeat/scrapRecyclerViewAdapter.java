package com.sw.todaeat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class scrapRecyclerViewAdapter extends RecyclerView.Adapter<scrapRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    ArrayList<scrapfolderNames> folderNamesArrayList=new ArrayList<scrapfolderNames>();

    public scrapRecyclerViewAdapter(Context context){
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context=parent.getContext();
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view=inflater.inflate(R.layout.scrap_folder_list_view_layout,parent,false);
        scrapRecyclerViewAdapter.ViewHolder vh = new scrapRecyclerViewAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        scrapfolderNames item=folderNamesArrayList.get(position);

        holder.folderName.setText(item.getFolderName());
        holder.deleteFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFolder(position);
            }
        });
    }

    @Override
    public int getItemCount(){
        return folderNamesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView folderName;
        Button deleteFolder;
        Button folderImage;

        ViewHolder(View itemView){
            super(itemView);

            folderName=itemView.findViewById(R.id.folderName);
            deleteFolder=itemView.findViewById(R.id.deleteFolder);
            folderImage=itemView.findViewById(R.id.folderImage);

            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        Intent intent = new Intent(mContext,scrapfoodListActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        intent.putExtra("folderName",folderNamesArrayList.get(pos).getFolderName());

                        mContext.startActivity(intent);

                    }

                }
            });

        }
    }

    public void addItem(String newFolderName){
        scrapfolderNames folder_name = new scrapfolderNames(newFolderName);
        folderNamesArrayList.add(folder_name);
    }

    public void deleteFolder(int position){
        folderNamesArrayList.remove(position);

        if(folderNamesArrayList.size()==0){
            scrapPage.info1.setVisibility(View.VISIBLE);
            scrapPage.info2.setVisibility(View.VISIBLE);
        }

        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

}
