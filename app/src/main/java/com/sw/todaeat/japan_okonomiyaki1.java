package com.sw.todaeat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

public class japan_okonomiyaki1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView cabbage, flour, fry_flour, water, egg;
    private Fraction cabbage_num = new Fraction(8);
    private Fraction cal_num = new Fraction(8);
    private int flour_num = 4;
    private int fry_flour_num = 2;
    private int water_num = 30;
    private int egg_num = 1;

    Button bookmark;
    boolean bookmark_pressed = false;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.japan_food_okonomi_2,"오꼬노미야끼",20)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.japan_okonomiyaki1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){
                    Intent intent = new Intent(japan_okonomiyaki1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);
                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.japan_food_okonomi_2,"오꼬노미야끼",20))){
                            scrapPage.scrapped.remove(item);
                        }
                    }
                    bookmark.setBackgroundResource(R.drawable.ic__bookmark_before_click);
                    bookmark_pressed = false;
                }

            }
        }) ;

        tvCount = findViewById(R.id.tv_count);
        tvCount.setText(count+"");

        cabbage = findViewById(R.id.cabbage_num);
        flour = findViewById(R.id.flour_num);
        fry_flour = findViewById(R.id.fry_flour_num);
        water = findViewById(R.id.water_num);
        egg = findViewById(R.id.egg_num);

        if(cabbage_num.getDenominator() == cabbage_num.getNumerator()){
            cabbage.setText(1+"");
        }
        else if(cabbage_num.getDenominator() == 1)
            cabbage.setText(cabbage_num.getNumerator()+"");
        else
            cabbage.setText(cabbage_num.getNumerator() + "/" + cabbage_num.getDenominator()+"");
        flour.setText(flour_num+"");
        fry_flour.setText(fry_flour_num+"");
        water.setText(water_num+"");
        egg.setText(egg_num+"");

        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            cabbage_num = Fraction.addFraction(cabbage_num, cal_num);
            flour_num += 4;
            fry_flour_num += 2;
            water_num += 30;
            egg_num += 1;

            if(cabbage_num.getDenominator() == cabbage_num.getNumerator()){
                cabbage.setText(1+"");
            }
            else if(cabbage_num.getDenominator() == 1)
                cabbage.setText(cabbage_num.getNumerator()+"");
            else
                cabbage.setText(cabbage_num.getNumerator() + "/" + cabbage_num.getDenominator()+"");
            flour.setText(flour_num+"");
            fry_flour.setText(fry_flour_num+"");
            water.setText(water_num+"");
            egg.setText(egg_num+"");
        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            cabbage_num = Fraction.subFraction(cabbage_num, cal_num);
            flour_num -= 4;
            fry_flour_num -= 2;
            water_num -= 30;
            egg_num -= 1;

            if(cabbage_num.getDenominator() == cabbage_num.getNumerator()){
                cabbage.setText(1+"");
            }
            else if(cabbage_num.getDenominator() == 1)
                cabbage.setText(cabbage_num.getNumerator()+"");
            else
                cabbage.setText(cabbage_num.getNumerator() + "/" + cabbage_num.getDenominator()+"");
            flour.setText(flour_num+"");
            fry_flour.setText(fry_flour_num+"");
            water.setText(water_num+"");
            egg.setText(egg_num+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), japan_Okonomiyakki2.class);
                startActivity(intent);
            }
        });

        FloatingActionButton cart = (FloatingActionButton)findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Memo_MainActivity.class);
                startActivity(intent);
            }
        });


        FloatingActionButton fridge =  (FloatingActionButton)findViewById(R.id.fridge);
        fridge.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //추가된 소스, ToolBar에 menu.xml을 인플레이트함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.bookmark:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "북마크로 이동", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), scrapPage.class);
                startActivity(intent1);
                return true;

            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(), MainActivity_japan.class);  // 눌렀을 때 메인화면으로 이동하기
                startActivity(intent);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}