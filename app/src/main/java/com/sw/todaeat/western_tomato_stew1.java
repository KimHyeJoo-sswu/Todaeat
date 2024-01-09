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


public class western_tomato_stew1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView tomato, potato, onion, tomato_juice, soy_sauce, vinegar, ketchup;
    private int tomato_num = 1;
    private Fraction potato_num = new Fraction(2);
    private Fraction onion_num = new Fraction(2);
    private int vinegar_num = 1;
    private Fraction tomato_juice_num = new Fraction(2,3);
    private Fraction two_three_cal_num = new Fraction(2, 3);
    private Fraction soy_sauce_num = new Fraction(2,3);
    private Fraction ketchup_num = new Fraction(3,2);
    private Fraction three_two_cal_num = new Fraction(3, 2);
    private Fraction half_cal_num = new Fraction(2);

    boolean bookmark_pressed = false;
    Button bookmark;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.tomato_stew,"토마토스튜",25)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.western_tomato_stew_1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");


        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(western_tomato_stew1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.tomato_stew,"토마토스튜",20))){
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

        tomato = findViewById(R.id.tomato_num);
        potato = findViewById(R.id.potato_num);
        tomato_juice = findViewById(R.id.tomato_juice_num);
        soy_sauce = findViewById(R.id.soy_sauce_num);
        ketchup = findViewById(R.id.ketchup_num);
        vinegar = findViewById(R.id.vinegar_num);
        onion = findViewById(R.id.onion_num);

        tomato.setText(tomato_num+"");
        if(potato_num.getDenominator() == potato_num.getNumerator()){
            potato.setText(1+"");
        }
        else if(potato_num.getDenominator() == 1)
            potato.setText(potato_num.getNumerator()+"");
        else
            potato.setText(potato_num.getNumerator() + "/" + potato_num.getDenominator()+"");
        if(onion_num.getDenominator() == onion_num.getNumerator()){
            onion.setText(1+"");
        }
        else if(onion_num.getDenominator() == 1)
            onion.setText(onion_num.getNumerator()+"");
        else
            onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
        if(tomato_juice_num.getDenominator() == tomato_juice_num.getNumerator()){
            tomato_juice.setText(1+"");
        }
        else if(tomato_juice_num.getDenominator() == 1)
            tomato_juice.setText(tomato_juice_num.getNumerator()+"");
        else
            tomato_juice.setText(tomato_juice_num.getNumerator() + "/" + tomato_juice_num.getDenominator()+"");

        if(soy_sauce_num.getDenominator() == soy_sauce_num.getNumerator()){
            soy_sauce.setText(1+"");
        }
        else if(soy_sauce_num.getDenominator() == 1)
            soy_sauce.setText(soy_sauce_num.getNumerator()+"");
        else
            soy_sauce.setText(soy_sauce_num.getNumerator() + "/" + soy_sauce_num.getDenominator()+"");

        if(ketchup_num.getDenominator() == ketchup_num.getNumerator()){
            ketchup.setText(1+"");
        }
        else if(ketchup_num.getDenominator() == 1)
            ketchup.setText(ketchup_num.getNumerator()+"");
        else
            ketchup.setText(ketchup_num.getNumerator() + "/" + ketchup_num.getDenominator()+"");
        vinegar.setText(vinegar_num+"");


        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            tomato_num += 1;
            potato_num = Fraction.addFraction(potato_num, half_cal_num);
            onion_num = Fraction.addFraction(onion_num, half_cal_num);
            tomato_juice_num = Fraction.addFraction(tomato_juice_num, two_three_cal_num);
            ketchup_num = Fraction.addFraction(ketchup_num, three_two_cal_num);
            soy_sauce_num = Fraction.addFraction(soy_sauce_num, two_three_cal_num);
            vinegar_num += 1;

            tomato.setText(tomato_num+"");
            if(potato_num.getDenominator() == potato_num.getNumerator()){
                potato.setText(1+"");
            }
            else if(potato_num.getDenominator() == 1)
                potato.setText(potato_num.getNumerator()+"");
            else
                potato.setText(potato_num.getNumerator() + "/" + potato_num.getDenominator()+"");
            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
            if(tomato_juice_num.getDenominator() == tomato_juice_num.getNumerator()){
                tomato_juice.setText(1+"");
            }
            else if(tomato_juice_num.getDenominator() == 1)
                tomato_juice.setText(tomato_juice_num.getNumerator()+"");
            else
                tomato_juice.setText(tomato_juice_num.getNumerator() + "/" + tomato_juice_num.getDenominator()+"");

            if(soy_sauce_num.getDenominator() == soy_sauce_num.getNumerator()){
                soy_sauce.setText(1+"");
            }
            else if(soy_sauce_num.getDenominator() == 1)
                soy_sauce.setText(soy_sauce_num.getNumerator()+"");
            else
                soy_sauce.setText(soy_sauce_num.getNumerator() + "/" + soy_sauce_num.getDenominator()+"");

            if(ketchup_num.getDenominator() == ketchup_num.getNumerator()){
                ketchup.setText(1+"");
            }
            else if(ketchup_num.getDenominator() == 1)
                ketchup.setText(ketchup_num.getNumerator()+"");
            else
                ketchup.setText(ketchup_num.getNumerator() + "/" + ketchup_num.getDenominator()+"");
            vinegar.setText(vinegar_num+"");

        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            tomato_num -= 1;
            potato_num = Fraction.subFraction(potato_num, half_cal_num);
            onion_num = Fraction.subFraction(onion_num, half_cal_num);
            tomato_juice_num = Fraction.subFraction(tomato_juice_num, two_three_cal_num);
            ketchup_num = Fraction.subFraction(ketchup_num, three_two_cal_num);
            soy_sauce_num = Fraction.subFraction(soy_sauce_num, two_three_cal_num);
            vinegar_num -= 1;

            tomato.setText(tomato_num+"");
            if(potato_num.getDenominator() == potato_num.getNumerator()){
                potato.setText(1+"");
            }
            else if(potato_num.getDenominator() == 1)
                potato.setText(potato_num.getNumerator()+"");
            else
                potato.setText(potato_num.getNumerator() + "/" + potato_num.getDenominator()+"");
            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");
            if(tomato_juice_num.getDenominator() == tomato_juice_num.getNumerator()){
                tomato_juice.setText(1+"");
            }
            else if(tomato_juice_num.getDenominator() == 1)
                tomato_juice.setText(tomato_juice_num.getNumerator()+"");
            else
                tomato_juice.setText(tomato_juice_num.getNumerator() + "/" + tomato_juice_num.getDenominator()+"");

            if(soy_sauce_num.getDenominator() == soy_sauce_num.getNumerator()){
                soy_sauce.setText(1+"");
            }
            else if(soy_sauce_num.getDenominator() == 1)
                soy_sauce.setText(soy_sauce_num.getNumerator()+"");
            else
                soy_sauce.setText(soy_sauce_num.getNumerator() + "/" + soy_sauce_num.getDenominator()+"");

            if(ketchup_num.getDenominator() == ketchup_num.getNumerator()){
                ketchup.setText(1+"");
            }
            else if(ketchup_num.getDenominator() == 1)
                ketchup.setText(ketchup_num.getNumerator()+"");
            else
                ketchup.setText(ketchup_num.getNumerator() + "/" + ketchup_num.getDenominator()+"");
            vinegar.setText(vinegar_num+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), western_Tomatostu2.class);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity_western.class);  // 눌렀을 때 메인화면으로 이동하기
                startActivity(intent);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}