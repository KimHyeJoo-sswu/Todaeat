package com.sw.todaeat;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class recipe_bar extends AppCompatActivity {

    public void openActivity() {
        Intent intent = new Intent(this, timer.class);
        startActivity(intent);
    }

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_bar);

        chipNavigationBar=findViewById(R.id.nav_bar);

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                if(i==R.id.bottom_nav_timer){
                    openActivity();
                    findViewById(R.id.bottom_nav_timer).setSelected(false);
                }
            }
        });

    }



}