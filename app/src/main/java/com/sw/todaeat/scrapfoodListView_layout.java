package com.sw.todaeat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class scrapfoodListView_layout extends AppCompatActivity {

    private Button deleteScrap;
    private ImageView foodImage;
    private TextView foodName;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_food_list_view_layout);

        deleteScrap=findViewById(R.id.deleteScrap);
        foodImage=findViewById(R.id.foodImage);
        foodName=findViewById(R.id.folderName);
        time=findViewById(R.id.time);

    }
}