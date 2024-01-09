package com.sw.todaeat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Categoryfood extends AppCompatActivity {
    //editText 부분
    EditText editText;
    Button selectIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryfood);

        //statusBarColorType 색상 바꾸기
        StatusBarUtils.setStatusBarColor(this, StatusBarUtils.StatusBarColorType.MAINCOLOR_STATUS_BAR);

        //
        selectIngredients=findViewById(R.id.imageMenu);
        selectIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        //japan_food 버튼 클릭 시 액티비티 전환
        Button japan_food =(Button) findViewById(R.id.japan_food);
        japan_food.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MainActivity_japan.class);
                startActivity(intent);
            }
        });

        //korea_food 버튼 클릭 시 액티비티 전환
        Button korea_food =(Button) findViewById(R.id.korea_food);
        korea_food.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MainActivity_korea.class);
                startActivity(intent);
            }
        });

        //china_food 버튼 클릭 시 액티비티 전환
        Button china_food =(Button) findViewById(R.id.china_food);
        china_food.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MainActivity_china.class);
                startActivity(intent);
            }
        });

        //sns_food 버튼 클릭 시 액티비티 전환
        Button sns_food =(Button) findViewById(R.id.sns_food);
        sns_food.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MainActivity_sns.class);
                startActivity(intent);
            }
        });

        //western_food 버튼 클릭 시 액티비티 전환
        Button western_food =(Button) findViewById(R.id.western_food);
        western_food.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MainActivity_western.class);
                startActivity(intent);
            }
        });

        //diet_food 버튼 클릭 시 액티비티 전환
        Button diet_food =(Button) findViewById(R.id.diet_food);
        diet_food.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MainActivity_diet.class);
                startActivity(intent);
            }
        });






    }

}