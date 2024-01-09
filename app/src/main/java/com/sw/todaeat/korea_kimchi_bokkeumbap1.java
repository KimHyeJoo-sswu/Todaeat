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

public class korea_kimchi_bokkeumbap1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView rice, kimchi, spam, green_onion, soy_sauce, pepper_powder, oil, egg;
    private int rice_num = 1;
    private Fraction kimchi_num = new Fraction(2);
    private Fraction spam_num = new Fraction(2);
    private Fraction green_onion_num = new Fraction(2);
    private int soy_sauce_num = 1;
    private Fraction pepper_powder_num = new Fraction(2);
    private int oil_num = 3;
    private int egg_num = 1;
    private Fraction half_cal_num = new Fraction(2);

    Button bookmark;
    boolean bookmark_pressed = false;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.korea_food_1_kimchibok,"김치볶음밥",20)));
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korea_kimchi_bokkeumbap1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(korea_kimchi_bokkeumbap1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.korea_food_1_kimchibok,"김치볶음밥",20))){
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


        kimchi = findViewById(R.id.kimchi_num);
        rice = findViewById(R.id.rice_num);
        spam = findViewById(R.id.spam_num);
        green_onion = findViewById(R.id.green_onion_num);
        soy_sauce = findViewById(R.id.soy_sauce_num);
        pepper_powder = findViewById(R.id.pepper_powder_num);
        oil = findViewById(R.id.oil_num);
        egg = findViewById(R.id.egg_num);


        rice.setText(rice_num+"");
        if(kimchi_num.getDenominator() == kimchi_num.getNumerator()){
            kimchi.setText(1+"");
        }
        else if(kimchi_num.getDenominator() == 1)
            kimchi.setText(kimchi_num.getNumerator()+"");
        else
            kimchi.setText(kimchi_num.getNumerator() + "/" + kimchi_num.getDenominator()+"");
        if(spam_num.getDenominator() == spam_num.getNumerator()){
            spam.setText(1+"");
        }
        else if(spam_num.getDenominator() == 1)
            spam.setText(spam_num.getNumerator()+"");
        else
            spam.setText(spam_num.getNumerator() + "/" + spam_num.getDenominator()+"");
        if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
            green_onion.setText(1+"");
        }
        else if(green_onion_num.getDenominator() == 1)
            green_onion.setText(green_onion_num.getNumerator()+"");
        else
            green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");
        soy_sauce.setText(soy_sauce_num+"");
        if(pepper_powder_num.getDenominator() == pepper_powder_num.getNumerator()){
            pepper_powder.setText(1+"");
        }
        else if(pepper_powder_num.getDenominator() == 1)
            pepper_powder.setText(pepper_powder_num.getNumerator()+"");
        else
            pepper_powder.setText(pepper_powder_num.getNumerator() + "/" + pepper_powder_num.getDenominator()+"");
        soy_sauce.setText(soy_sauce_num+"");
        oil.setText(oil_num+"");
        egg.setText(egg_num+"");

        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            rice_num += 1;
            kimchi_num = Fraction.addFraction(kimchi_num, half_cal_num);
            spam_num = Fraction.addFraction(spam_num, half_cal_num);
            green_onion_num = Fraction.addFraction(green_onion_num, half_cal_num);
            soy_sauce_num += 1;
            pepper_powder_num = Fraction.addFraction(pepper_powder_num, half_cal_num);
            oil_num += 3;
            egg_num += 1;

            rice.setText(rice_num+"");
            if(kimchi_num.getDenominator() == kimchi_num.getNumerator()){
                kimchi.setText(1+"");
            }
            else if(kimchi_num.getDenominator() == 1)
                kimchi.setText(kimchi_num.getNumerator()+"");
            else
                kimchi.setText(kimchi_num.getNumerator() + "/" + kimchi_num.getDenominator()+"");
            if(spam_num.getDenominator() == spam_num.getNumerator()){
                spam.setText(1+"");
            }
            else if(spam_num.getDenominator() == 1)
                spam.setText(spam_num.getNumerator()+"");
            else
                spam.setText(spam_num.getNumerator() + "/" + spam_num.getDenominator()+"");
            if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");
            soy_sauce.setText(soy_sauce_num+"");
            if(pepper_powder_num.getDenominator() == pepper_powder_num.getNumerator()){
                pepper_powder.setText(1+"");
            }
            else if(pepper_powder_num.getDenominator() == 1)
                pepper_powder.setText(pepper_powder_num.getNumerator()+"");
            else
                pepper_powder.setText(pepper_powder_num.getNumerator() + "/" + pepper_powder_num.getDenominator()+"");
            soy_sauce.setText(soy_sauce_num+"");
            oil.setText(oil_num+"");
            egg.setText(egg_num+"");;
        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            rice_num -= 1;
            kimchi_num = Fraction.subFraction(kimchi_num, half_cal_num);
            spam_num = Fraction.subFraction(spam_num, half_cal_num);
            green_onion_num = Fraction.subFraction(green_onion_num, half_cal_num);
            soy_sauce_num -= 1;
            pepper_powder_num = Fraction.subFraction(pepper_powder_num, half_cal_num);
            oil_num -= 3;
            egg_num -= 1;

            rice.setText(rice_num+"");
            if(kimchi_num.getDenominator() == kimchi_num.getNumerator()){
                kimchi.setText(1+"");
            }
            else if(kimchi_num.getDenominator() == 1)
                kimchi.setText(kimchi_num.getNumerator()+"");
            else
                kimchi.setText(kimchi_num.getNumerator() + "/" + kimchi_num.getDenominator()+"");
            if(spam_num.getDenominator() == spam_num.getNumerator()){
                spam.setText(1+"");
            }
            else if(spam_num.getDenominator() == 1)
                spam.setText(spam_num.getNumerator()+"");
            else
                spam.setText(spam_num.getNumerator() + "/" + spam_num.getDenominator()+"");

            if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");
            soy_sauce.setText(soy_sauce_num+"");
            if(pepper_powder_num.getDenominator() == pepper_powder_num.getNumerator()){
                pepper_powder.setText(1+"");
            }
            else if(pepper_powder_num.getDenominator() == 1)
                pepper_powder.setText(pepper_powder_num.getNumerator()+"");
            else
                pepper_powder.setText(pepper_powder_num.getNumerator() + "/" + pepper_powder_num.getDenominator()+"");
            soy_sauce.setText(soy_sauce_num+"");
            oil.setText(oil_num+"");
            egg.setText(egg_num+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), korea_KimchiBok2.class);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity_korea.class);  // 눌렀을 때 메인화면으로 이동하기
                startActivity(intent);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}