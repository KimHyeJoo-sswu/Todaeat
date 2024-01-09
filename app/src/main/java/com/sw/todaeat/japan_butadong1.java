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

import static com.sw.todaeat.scrapPage.mDBOpenHelper;


public class japan_butadong1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView pork, onion, green_onion, egg, soy_sauce, oyster_sauce, tsuyuu, sugar, chungjuu, water;
    private int pork_num = 150;
    private Fraction onion_num = new Fraction(2);
    private Fraction green_onion_num = new Fraction(2);
    private int egg_num = 2;
    private int soy_sauce_num = 1;
    private Fraction oyster_sauce_num = new Fraction(2);
    private int tsuyuu_num = 2;
    private Fraction sugar_num = new Fraction(2);
    private Fraction chungjuu_num = new Fraction(2);
    private int water_num = 5;
    private Fraction half_cal_num = new Fraction(2);

    boolean bookmark_pressed = false;
    Button bookmark;

    ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.japan_food_butadong_3,"부타동",25)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.japan_butadong_1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");


        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(japan_butadong1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.japan_food_butadong_3,"부타동",25))){
                            scrapPage.scrapped.remove(item);
                        }
                    }

                    bookmark.setBackgroundResource(R.drawable.ic__bookmark_before_click);
                    bookmark_pressed=false;
                }
            }
        }) ;


        tvCount = findViewById(R.id.tv_count);
        tvCount.setText(count+"");

        pork = findViewById(R.id.pork_num);
        onion = findViewById(R.id.onion_num);
        green_onion = findViewById(R.id.green_onion_num);
        egg = findViewById(R.id.egg_num);
        oyster_sauce = findViewById(R.id.oyster_sauce_num);
        tsuyuu = findViewById(R.id.tsuyuu_num);
        chungjuu = findViewById(R.id.chungjuu_num);
        water = findViewById(R.id.water_num);
        soy_sauce = findViewById(R.id.soy_sauce_num);
        sugar = findViewById(R.id.sugar_num);



        pork.setText(pork_num+"");
        if(onion_num.getDenominator() == onion_num.getNumerator()){
            onion.setText(1+"");
        }
        else if(onion_num.getDenominator() == 1)
            onion.setText(onion_num.getNumerator()+"");
        else
            onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
        if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
            green_onion.setText(1+"");
        }
        else if(green_onion_num.getDenominator() == 1)
            green_onion.setText(green_onion_num.getNumerator()+"");
        else
            onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
        egg.setText(egg_num+"");
        soy_sauce.setText(soy_sauce_num+"");
        if(oyster_sauce_num.getDenominator() == oyster_sauce_num.getNumerator()){
            oyster_sauce.setText(1+"");
        }
        else if(oyster_sauce_num.getDenominator() == 1)
            oyster_sauce.setText(oyster_sauce_num.getNumerator()+"");
        else
            oyster_sauce.setText(oyster_sauce_num.getNumerator() + "/" + oyster_sauce_num.getDenominator()+"");
        tsuyuu.setText(tsuyuu_num+"");
        if(sugar_num.getDenominator() == sugar_num.getNumerator()){
            sugar.setText(1+"");
        }
        else if(sugar_num.getDenominator() == 1)
            sugar.setText(sugar_num.getNumerator()+"");
        else
            sugar.setText(sugar_num.getNumerator() + "/" + sugar_num.getDenominator()+"");
        if(chungjuu_num.getDenominator() == chungjuu_num.getNumerator()){
            chungjuu.setText(1+"");
        }
        else if(chungjuu_num.getDenominator() == 1)
            chungjuu.setText(chungjuu_num.getNumerator()+"");
        else
            chungjuu.setText(chungjuu_num.getNumerator() + "/" + chungjuu_num.getDenominator()+"");
        water.setText(water_num+"");

        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            pork_num += 150;
            onion_num = Fraction.addFraction(onion_num, half_cal_num);
            green_onion_num = Fraction.addFraction(green_onion_num, half_cal_num);
            egg_num += 2;
            soy_sauce_num += 1;
            oyster_sauce_num = Fraction.addFraction(oyster_sauce_num, half_cal_num);
            tsuyuu_num += 2;
            chungjuu_num = Fraction.addFraction(chungjuu_num,half_cal_num);
            sugar_num = Fraction.addFraction(sugar_num, half_cal_num);
            water_num += 5;
            soy_sauce_num += 2;



            pork.setText(pork_num+"");
            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
            if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
            egg.setText(egg_num+"");
            soy_sauce.setText(soy_sauce_num+"");
            if(oyster_sauce_num.getDenominator() == oyster_sauce_num.getNumerator()){
                oyster_sauce.setText(1+"");
            }
            else if(oyster_sauce_num.getDenominator() == 1)
                oyster_sauce.setText(oyster_sauce_num.getNumerator()+"");
            else
                oyster_sauce.setText(oyster_sauce_num.getNumerator() + "/" + oyster_sauce_num.getDenominator()+"");
            tsuyuu.setText(tsuyuu_num+"");
            if(sugar_num.getDenominator() == sugar_num.getNumerator()){
                sugar.setText(1+"");
            }
            else if(sugar_num.getDenominator() == 1)
                sugar.setText(sugar_num.getNumerator()+"");
            else
                sugar.setText(sugar_num.getNumerator() + "/" + sugar_num.getDenominator()+"");
            if(chungjuu_num.getDenominator() == chungjuu_num.getNumerator()){
                chungjuu.setText(1+"");
            }
            else if(chungjuu_num.getDenominator() == 1)
                chungjuu.setText(chungjuu_num.getNumerator()+"");
            else
                chungjuu.setText(chungjuu_num.getNumerator() + "/" + chungjuu_num.getDenominator()+"");
            water.setText(water_num+"");
        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            pork_num -= 150;
            onion_num = Fraction.subFraction(onion_num, half_cal_num);
            green_onion_num = Fraction.subFraction(green_onion_num, half_cal_num);
            egg_num -= 2;
            soy_sauce_num -= 1;
            oyster_sauce_num = Fraction.subFraction(oyster_sauce_num, half_cal_num);
            tsuyuu_num -= 2;
            chungjuu_num = Fraction.subFraction(chungjuu_num,half_cal_num);
            sugar_num = Fraction.subFraction(sugar_num, half_cal_num);
            water_num -= 5;
            soy_sauce_num -= 2;

            pork.setText(pork_num+"");
            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
            if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
            egg.setText(egg_num+"");
            soy_sauce.setText(soy_sauce_num+"");
            if(oyster_sauce_num.getDenominator() == oyster_sauce_num.getNumerator()){
                oyster_sauce.setText(1+"");
            }
            else if(oyster_sauce_num.getDenominator() == 1)
                oyster_sauce.setText(oyster_sauce_num.getNumerator()+"");
            else
                oyster_sauce.setText(oyster_sauce_num.getNumerator() + "/" + oyster_sauce_num.getDenominator()+"");
            tsuyuu.setText(tsuyuu_num+"");
            if(sugar_num.getDenominator() == sugar_num.getNumerator()){
                sugar.setText(1+"");
            }
            else if(sugar_num.getDenominator() == 1)
                sugar.setText(sugar_num.getNumerator()+"");
            else
                sugar.setText(sugar_num.getNumerator() + "/" + sugar_num.getDenominator()+"");
            if(chungjuu_num.getDenominator() == chungjuu_num.getNumerator()){
                chungjuu.setText(1+"");
            }
            else if(chungjuu_num.getDenominator() == 1)
                chungjuu.setText(chungjuu_num.getNumerator()+"");
            else
                chungjuu.setText(chungjuu_num.getNumerator() + "/" + chungjuu_num.getDenominator()+"");
            water.setText(water_num+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), japan_Butadong2.class);
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