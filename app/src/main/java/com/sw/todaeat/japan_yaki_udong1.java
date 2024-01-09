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


public class japan_yaki_udong1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView udong, pork, onion, carrot, cabbage, green_onion, oil, water, soy_sauce, vinegar, sugar;
    private int udong_num = 1;
    private int pork_num = 50;
    private Fraction onion_num = new Fraction(8);
    private Fraction onion_cal_num = new Fraction(8);
    private Fraction carrot_num = new Fraction(2);
    private Fraction carrot_cal_num = new Fraction(2);
    private int cabbage_num = 1;
    private Fraction green_onion_num = new Fraction(6);
    private Fraction green_cal_num = new Fraction(6);
    private int oil_num = 2;
    private int water_num = 100;
    private int soy_sauce_num = 2;
    private int vinegar_num = 1;
    private int sugar_num = 1;

    Button bookmark;
    boolean bookmark_pressed = false;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.japan_food_bokudon_4,"볶음우동",20)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.japan_yaki_udong1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){
                    Intent intent = new Intent(japan_yaki_udong1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);
                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.japan_food_butadong_3,"볶음우동",25))){
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


        udong = findViewById(R.id.udong_num);
        pork = findViewById(R.id.pork_num);
        onion = findViewById(R.id.onion_num);
        carrot = findViewById(R.id.carrot_num);
        cabbage = findViewById(R.id.cabbage_num);
        green_onion = findViewById(R.id.green_onion_num);
        oil = findViewById(R.id.oil_num);
        water = findViewById(R.id.water_num);
        soy_sauce = findViewById(R.id.soy_sauce_num);
        vinegar = findViewById(R.id.vinegar_num);
        sugar = findViewById(R.id.sugar_num);


        udong.setText(udong_num+"");
        pork.setText(pork_num+"");
        if(onion_num.getDenominator() == onion_num.getNumerator()){
            onion.setText(1+"");
        }
        else if(onion_num.getDenominator() == 1)
            onion.setText(onion_num.getNumerator()+"");
        else
            onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
        if(carrot_num.getDenominator() == carrot_num.getNumerator()){
            carrot.setText(1+"");
        }
        else if(carrot_num.getDenominator() == 1)
            carrot.setText(carrot_num.getNumerator()+"");
        else
            carrot.setText(carrot_num.getNumerator() + "/" + carrot_num.getDenominator()+"");
        cabbage.setText(cabbage_num+"");
        if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
            green_onion.setText(1+"");
        }
        else if(green_onion_num.getDenominator() == 1)
            green_onion.setText(green_onion_num.getNumerator()+"");
        else
            green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");
        oil.setText(oil_num+"");
        sugar.setText(sugar_num+"");
        water.setText(water_num+"");
        vinegar.setText(vinegar_num+"");
        soy_sauce.setText(soy_sauce_num+"");


        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            udong_num += 1;
            pork_num += 50;
            onion_num = Fraction.addFraction(onion_num, onion_cal_num);
            carrot_num = Fraction.addFraction(carrot_num, carrot_cal_num);
            green_onion_num = Fraction.addFraction(green_onion_num, green_cal_num);
            oil_num += 2;
            water_num += 100;
            soy_sauce_num += 2;
            vinegar_num += 1;
            sugar_num += 1;
            cabbage_num += 1;

            udong.setText(udong_num+"");
            pork.setText(pork_num+"");
            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
            if(carrot_num.getDenominator() == carrot_num.getNumerator()){
                carrot.setText(1+"");
            }
            else if(carrot_num.getDenominator() == 1)
                carrot.setText(carrot_num.getNumerator()+"");
            else
                carrot.setText(carrot_num.getNumerator() + "/" + carrot_num.getDenominator()+"");
            cabbage.setText(cabbage_num+"");
            if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");
            oil.setText(oil_num+"");
            sugar.setText(sugar_num+"");
            water.setText(water_num+"");
            vinegar.setText(vinegar_num+"");
            soy_sauce.setText(soy_sauce_num+"");
        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            udong_num -= 1;
            pork_num -= 50;
            onion_num = Fraction.subFraction(onion_num, onion_cal_num);
            carrot_num = Fraction.subFraction(carrot_num, carrot_cal_num);
            green_onion_num = Fraction.subFraction(green_onion_num, green_cal_num);
            oil_num -= 2;
            water_num -= 100;
            soy_sauce_num -= 2;
            vinegar_num -= 1;
            sugar_num -= 1;
            cabbage_num -= 1;

            udong.setText(udong_num+"");
            pork.setText(pork_num+"");
            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
            if(carrot_num.getDenominator() == carrot_num.getNumerator()){
                carrot.setText(1+"");
            }
            else if(carrot_num.getDenominator() == 1)
                carrot.setText(carrot_num.getNumerator()+"");
            else
                carrot.setText(carrot_num.getNumerator() + "/" + carrot_num.getDenominator()+"");
            cabbage.setText(cabbage_num+"");
            if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");
            oil.setText(oil_num+"");
            sugar.setText(sugar_num+"");
            water.setText(water_num+"");
            vinegar.setText(vinegar_num+"");
            soy_sauce.setText(soy_sauce_num+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), japan_yakiUdong2.class);
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