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

public class korea_kkacdugi_bokkeumbap1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView rice, kkacdugi, spam, kkacdugi_sauce, pepper_powder, oyster_sauce, oil;
    private int rice_num = 1;
    private Fraction kkacdugi_num = new Fraction(2);
    private Fraction spam_num = new Fraction(2);
    private int kkacdugi_sauce_num = 4;
    private Fraction pepper_powder_num = new Fraction(2);
    private int oil_num = 3;
    private Fraction oyster_sauce_num = new Fraction(3);
    private Fraction oyster_sauce_cal_num = new Fraction(3);
    private Fraction half_cal_num = new Fraction(2);

    Button bookmark;
    boolean bookmark_pressed = false;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.korea_food_2_kakkdigibok,"깍두기볶음밥",15)));
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korea_kkacdugi_bokkeumbap1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(korea_kkacdugi_bokkeumbap1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.korea_food_2_kakkdigibok,"깍두기볶음밥",15))){
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


        kkacdugi = findViewById(R.id.kkacdugi_num);
        rice = findViewById(R.id.rice_num);
        spam = findViewById(R.id.spam_num);
        kkacdugi_sauce = findViewById(R.id.kkacdugi_sauce_num);
        pepper_powder = findViewById(R.id.pepper_powder_num);
        oyster_sauce = findViewById(R.id.oyster_sauce_num);
        oil = findViewById(R.id.oil_num);

        rice.setText(rice_num+"");
        if(kkacdugi_num.getDenominator() == kkacdugi_num.getNumerator()){
            kkacdugi.setText(1+"");
        }
        else if(kkacdugi_num.getDenominator() == 1)
            kkacdugi.setText(kkacdugi_num.getNumerator()+"");
        else
            kkacdugi.setText(kkacdugi_num.getNumerator() + "/" + kkacdugi_num.getDenominator()+"");

        if(spam_num.getDenominator() == spam_num.getNumerator()){
            spam.setText(1+"");
        }
        else if(spam_num.getDenominator() == 1)
            spam.setText(spam_num.getNumerator()+"");
        else
            spam.setText(spam_num.getNumerator() + "/" + spam_num.getDenominator()+"");

        kkacdugi_sauce.setText(kkacdugi_sauce_num+"");

        if(pepper_powder_num.getDenominator() == pepper_powder_num.getNumerator()){
            pepper_powder.setText(1+"");
        }
        else if(pepper_powder_num.getDenominator() == 1)
            pepper_powder.setText(pepper_powder_num.getNumerator()+"");
        else
            pepper_powder.setText(pepper_powder_num.getNumerator() + "/" + pepper_powder_num.getDenominator()+"");

        if(oyster_sauce_num.getDenominator() == oyster_sauce_num.getNumerator()){
            oyster_sauce.setText(1+"");
        }
        else if(oyster_sauce_num.getDenominator() == 1)
            oyster_sauce.setText(oyster_sauce_num.getNumerator()+"");
        else
            oyster_sauce.setText(oyster_sauce_num.getNumerator() + "/" + oyster_sauce_num.getDenominator()+"");

        oil.setText(oil_num+"");


        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            rice_num += 1;
            kkacdugi_num = Fraction.addFraction(kkacdugi_num, half_cal_num);
            spam_num = Fraction.addFraction(spam_num, half_cal_num);
            kkacdugi_sauce_num += 4;
            oyster_sauce_num =  Fraction.addFraction(oyster_sauce_num, oyster_sauce_cal_num);
            pepper_powder_num = Fraction.addFraction(pepper_powder_num, half_cal_num);
            oil_num += 3;

            rice.setText(rice_num+"");
            if(kkacdugi_num.getDenominator() == kkacdugi_num.getNumerator()){
                kkacdugi.setText(1+"");
            }
            else if(kkacdugi_num.getDenominator() == 1)
                kkacdugi.setText(kkacdugi_num.getNumerator()+"");
            else
                kkacdugi.setText(kkacdugi_num.getNumerator() + "/" + kkacdugi_num.getDenominator()+"");
            if(spam_num.getDenominator() == spam_num.getNumerator()){
                spam.setText(1+"");
            }
            else if(spam_num.getDenominator() == 1)
                spam.setText(spam_num.getNumerator()+"");
            else
                spam.setText(spam_num.getNumerator() + "/" + spam_num.getDenominator()+"");
            kkacdugi_sauce.setText(kkacdugi_sauce_num+"");
            if(pepper_powder_num.getDenominator() == pepper_powder_num.getNumerator()){
                pepper_powder.setText(1+"");
            }
            else if(pepper_powder_num.getDenominator() == 1)
                pepper_powder.setText(pepper_powder_num.getNumerator()+"");
            else
                pepper_powder.setText(pepper_powder_num.getNumerator() + "/" + pepper_powder_num.getDenominator()+"");
            if(oyster_sauce_num.getDenominator() == oyster_sauce_num.getNumerator()){
                oyster_sauce.setText(1+"");
            }
            else if(oyster_sauce_num.getDenominator() == 1)
                oyster_sauce.setText(oyster_sauce_num.getNumerator()+"");
            else
                oyster_sauce.setText(oyster_sauce_num.getNumerator() + "/" + oyster_sauce_num.getDenominator()+"");
            oil.setText(oil_num+"");
        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            rice_num -= 1;
            kkacdugi_num = Fraction.subFraction(kkacdugi_num, half_cal_num);
            spam_num = Fraction.subFraction(spam_num, half_cal_num);
            kkacdugi_sauce_num -= 4;
            oyster_sauce_num =  Fraction.subFraction(oyster_sauce_num, oyster_sauce_cal_num);
            pepper_powder_num = Fraction.subFraction(pepper_powder_num, half_cal_num);
            oil_num -= 3;

            rice.setText(rice_num+"");
            if(kkacdugi_num.getDenominator() == kkacdugi_num.getNumerator()){
                kkacdugi.setText(1+"");
            }
            else if(kkacdugi_num.getDenominator() == 1)
                kkacdugi.setText(kkacdugi_num.getNumerator()+"");
            else
                kkacdugi.setText(kkacdugi_num.getNumerator() + "/" + kkacdugi_num.getDenominator()+"");
            if(spam_num.getDenominator() == spam_num.getNumerator()){
                spam.setText(1+"");
            }
            else if(spam_num.getDenominator() == 1)
                spam.setText(spam_num.getNumerator()+"");
            else
                spam.setText(spam_num.getNumerator() + "/" + spam_num.getDenominator()+"");
            kkacdugi_sauce.setText(kkacdugi_sauce_num+"");

            if(oyster_sauce_num.getDenominator() == oyster_sauce_num.getNumerator()){
                oyster_sauce.setText(1+"");
            }
            else if(oyster_sauce_num.getDenominator() == 1)
                oyster_sauce.setText(oyster_sauce_num.getNumerator()+"");
            else
                oyster_sauce.setText(oyster_sauce_num.getNumerator() + "/" + oyster_sauce_num.getDenominator()+"");

            if(pepper_powder_num.getDenominator() == pepper_powder_num.getNumerator()){
                pepper_powder.setText(1+"");
            }
            else if(pepper_powder_num.getDenominator() == 1)
                pepper_powder.setText(pepper_powder_num.getNumerator()+"");
            else
                pepper_powder.setText(pepper_powder_num.getNumerator() + "/" + pepper_powder_num.getDenominator()+"");
            oil.setText(oil_num+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), korea_KkakdugiBok2.class);
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