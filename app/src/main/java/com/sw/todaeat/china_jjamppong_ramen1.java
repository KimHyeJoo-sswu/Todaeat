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

public class china_jjamppong_ramen1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView ramyeon, green_onion, cabbage, onion,  soy_sauce, pepper_powder;
    private int ramyeon_num = 1;
    private Fraction add_num = new Fraction(2);
    private Fraction green_onion_num = new Fraction(2);
    private Fraction cabbage_num = new Fraction(2);
    private Fraction onion_num = new Fraction(2);
    private int soy_sauce_num = 1;
    private int pepper_powder_num = 1;

    boolean bookmark_pressed = false;
    Button bookmark;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.china_food_5_ramyeon_jjamppong,"짬뽕라면",15)));
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.china_jjamppongramen_1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(china_jjamppong_ramen1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.china_food_5_ramyeon_jjamppong,"짬뽕라면",15))){
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

        ramyeon = findViewById(R.id.ramyeon_num);
        green_onion = findViewById(R.id.green_onion_num);
        cabbage = findViewById(R.id.cabbage_num);
        onion = findViewById(R.id.onion_num);
        pepper_powder = findViewById(R.id.pepper_powder_num);
        soy_sauce = findViewById(R.id.soy_sauce_num);

        ramyeon.setText(ramyeon_num+"");
        if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
            green_onion.setText(1+"");
        }
        else if(green_onion_num.getDenominator() == 1)
            green_onion.setText(green_onion_num.getNumerator()+"");
        else
            green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");
        if(cabbage_num.getDenominator() == cabbage_num.getNumerator()){
            cabbage.setText(1+"");
        }
        else if(cabbage_num.getDenominator() == 1)
            cabbage.setText(cabbage_num.getNumerator()+"");
        else
            cabbage.setText(cabbage_num.getNumerator() + "/" + cabbage_num.getDenominator()+"");
        if(onion_num.getDenominator() == onion_num.getNumerator()){
            onion.setText(1+"");
        }
        else if(onion_num.getDenominator() == 1)
            onion.setText(cabbage_num.getNumerator()+"");
        else
            onion.setText(cabbage_num.getNumerator() + "/" + cabbage_num.getDenominator()+"");
        soy_sauce.setText(soy_sauce_num+"");
        pepper_powder.setText(pepper_powder_num+"");


        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            ramyeon_num += 1;
            green_onion_num = Fraction.addFraction(green_onion_num, add_num);
            cabbage_num = Fraction.addFraction(cabbage_num, add_num);
            onion_num = Fraction.addFraction(onion_num, add_num);
            soy_sauce_num += 1;
            pepper_powder_num += 1;

            ramyeon.setText(ramyeon_num+"");
            if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");
            if(cabbage_num.getDenominator() == cabbage_num.getNumerator()){
                cabbage.setText(1+"");
            }
            else if(cabbage_num.getDenominator() == 1)
                cabbage.setText(cabbage_num.getNumerator()+"");
            else
                cabbage.setText(cabbage_num.getNumerator() + "/" + cabbage_num.getDenominator()+"");
            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(cabbage_num.getNumerator()+"");
            else
                onion.setText(cabbage_num.getNumerator() + "/" + cabbage_num.getDenominator()+"");
            soy_sauce.setText(soy_sauce_num+"");
            pepper_powder.setText(pepper_powder_num+"");
        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            ramyeon_num -= 1;
            green_onion_num = Fraction.subFraction(green_onion_num, add_num);
            cabbage_num = Fraction.subFraction(cabbage_num, add_num);
            onion_num = Fraction.subFraction(onion_num, add_num);
            soy_sauce_num -= 1;
            pepper_powder_num -= 1;

            ramyeon.setText(ramyeon_num+"");
            if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");
            if(cabbage_num.getDenominator() == cabbage_num.getNumerator()){
                cabbage.setText(1+"");
            }
            else if(cabbage_num.getDenominator() == 1)
                cabbage.setText(cabbage_num.getNumerator()+"");
            else
                cabbage.setText(cabbage_num.getNumerator() + "/" + cabbage_num.getDenominator()+"");
            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(cabbage_num.getNumerator()+"");
            else
                onion.setText(cabbage_num.getNumerator() + "/" + cabbage_num.getDenominator()+"");
            soy_sauce.setText(soy_sauce_num+"");
            pepper_powder.setText(pepper_powder_num+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), china_jjamppongramen2.class);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity_china.class);  // 눌렀을 때 메인화면으로 이동하기
                startActivity(intent);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}