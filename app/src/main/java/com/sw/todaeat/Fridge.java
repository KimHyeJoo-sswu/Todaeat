package com.sw.todaeat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Fridge extends AppCompatActivity {

    Button close;
    TextView inFridge;
    String ingredients="";
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.START;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        close=findViewById(R.id.close);
        inFridge=findViewById(R.id.inFridge);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        for(ingredients item : MainActivity.fridge){
            count++;
            if(count==MainActivity.fridge.size())
                ingredients = ingredients + item.getIngredientName();
            else
                ingredients = ingredients + item.getIngredientName() + ", ";
        }

        inFridge.setText(ingredients);
    }
}