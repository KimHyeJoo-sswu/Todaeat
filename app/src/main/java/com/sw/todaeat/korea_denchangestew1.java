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


public class korea_denchangestew1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView rice_water, soybean_paste, dajin_garlic, pepper_powder, potato, green_pumpkin, mushroom, tofu, onion, chungyang_pepper, green_onion;
    private int rice_water_num = 4;
    private int soybean_paste_num = 4;
    private Fraction dajin_garlic_num = new Fraction(2);
    private Fraction pepper_powder_num = new Fraction(2);
    private Fraction half_cal_num = new Fraction(2);
    private int potato_num = 1;
    private Fraction green_pumpkin_num = new Fraction(2);
    private int mushroom_num = 100;
    private Fraction tofu_num = new Fraction(2);
    private Fraction onion_num = new Fraction(3);
    private Fraction onion_cal_num = new Fraction(3);
    private int chungyang_pepper_num = 2;
    private Fraction green_onion_num = new Fraction(4);
    private Fraction green_onion_cal_num = new Fraction(4);

    Button bookmark;
    boolean bookmark_pressed = false;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.korea_food_3_denchang,"된장찌개",15)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korea_denchangstew1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(korea_denchangestew1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.korea_food_3_denchang,"된장찌개",15))){
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


        rice_water = findViewById(R.id.rice_water_num);
        soybean_paste = findViewById(R.id.soybean_paste_num);
        dajin_garlic = findViewById(R.id.dajin_garlic_num);
        pepper_powder = findViewById(R.id.pepper_powder_num);
        potato = findViewById(R.id.potato_num);
        green_pumpkin = findViewById(R.id.green_pumpkin_num);
        mushroom = findViewById(R.id.mushroom_num);
        tofu = findViewById(R.id.tofu_num);
        onion = findViewById(R.id.onion_num);
        chungyang_pepper = findViewById(R.id.chungyang_pepper_num);
        green_onion = findViewById(R.id.green_onion_num);

        rice_water.setText(rice_water_num+"");
        soybean_paste.setText(soybean_paste_num+"");
        if(dajin_garlic_num.getDenominator() == dajin_garlic_num.getNumerator()){
            dajin_garlic.setText(1+"");
        }
        else if(dajin_garlic_num.getDenominator() == 1)
            dajin_garlic.setText(dajin_garlic_num.getNumerator()+"");
        else
            dajin_garlic.setText(dajin_garlic_num.getNumerator() + "/" + dajin_garlic_num.getDenominator()+"");
        if(pepper_powder_num.getDenominator() == pepper_powder_num.getNumerator()){
            pepper_powder.setText(1+"");
        }
        else if(pepper_powder_num.getDenominator() == 1)
            pepper_powder.setText(pepper_powder_num.getNumerator()+"");
        else
            pepper_powder.setText(pepper_powder_num.getNumerator() + "/" + pepper_powder_num.getDenominator()+"");
        potato.setText(potato_num+"");
        if(green_pumpkin_num.getDenominator() == green_pumpkin_num.getNumerator()){
            green_pumpkin.setText(1+"");
        }
        else if(green_pumpkin_num.getDenominator() == 1)
            green_pumpkin.setText(green_pumpkin_num.getNumerator()+"");
        else
            green_pumpkin.setText(green_pumpkin_num.getNumerator() + "/" + green_pumpkin_num.getDenominator()+"");

        mushroom.setText(mushroom_num+"");

        if(tofu_num.getDenominator() == tofu_num.getNumerator()){
            tofu.setText(1+"");
        }
        else if(tofu_num.getDenominator() == 1)
            tofu.setText(tofu_num.getNumerator()+"");
        else
            tofu.setText(tofu_num.getNumerator() + "/" + tofu_num.getDenominator()+"");

        if(onion_num.getDenominator() == onion_num.getNumerator()){
            onion.setText(1+"");
        }
        else if(onion_num.getDenominator() == 1)
            onion.setText(onion_num.getNumerator()+"");
        else
            onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");

        chungyang_pepper.setText(chungyang_pepper_num+"");
        if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
            green_onion.setText(1+"");
        }
        else if(green_onion_num.getDenominator() == 1)
            green_onion.setText(green_onion_num.getNumerator()+"");
        else
            green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");

        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            rice_water_num += 4;
            soybean_paste_num += 4;
            dajin_garlic_num = Fraction.addFraction(dajin_garlic_num, half_cal_num);
            potato_num += 1;
            green_pumpkin_num = Fraction.addFraction(green_pumpkin_num, half_cal_num);
            mushroom_num += 100;
            tofu_num = Fraction.addFraction(tofu_num, half_cal_num);
            onion_num = Fraction.addFraction(onion_num, onion_cal_num);
            chungyang_pepper_num += 2;
            green_onion_num = Fraction.addFraction(green_onion_num, green_onion_cal_num);

            rice_water.setText(rice_water_num+"");
            soybean_paste.setText(soybean_paste_num+"");
            if(dajin_garlic_num.getDenominator() == dajin_garlic_num.getNumerator()){
                dajin_garlic.setText(1+"");
            }
            else if(dajin_garlic_num.getDenominator() == 1)
                dajin_garlic.setText(dajin_garlic_num.getNumerator()+"");
            else
                dajin_garlic.setText(dajin_garlic_num.getNumerator() + "/" + dajin_garlic_num.getDenominator()+"");
            if(pepper_powder_num.getDenominator() == pepper_powder_num.getNumerator()){
                pepper_powder.setText(1+"");
            }
            else if(pepper_powder_num.getDenominator() == 1)
                pepper_powder.setText(pepper_powder_num.getNumerator()+"");
            else
                pepper_powder.setText(pepper_powder_num.getNumerator() + "/" + pepper_powder_num.getDenominator()+"");
            potato.setText(potato_num+"");
            if(green_pumpkin_num.getDenominator() == green_pumpkin_num.getNumerator()){
                green_pumpkin.setText(1+"");
            }
            else if(green_pumpkin_num.getDenominator() == 1)
                green_pumpkin.setText(green_pumpkin_num.getNumerator()+"");
            else
                green_pumpkin.setText(green_pumpkin_num.getNumerator() + "/" + green_pumpkin_num.getDenominator()+"");

            mushroom.setText(mushroom_num+"");

            if(tofu_num.getDenominator() == tofu_num.getNumerator()){
                tofu.setText(1+"");
            }
            else if(tofu_num.getDenominator() == 1)
                tofu.setText(tofu_num.getNumerator()+"");
            else
                tofu.setText(tofu_num.getNumerator() + "/" + tofu_num.getDenominator()+"");

            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");

            chungyang_pepper.setText(chungyang_pepper_num+"");
            if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");

        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            rice_water_num -= 4;
            soybean_paste_num -= 4;
            dajin_garlic_num = Fraction.subFraction(dajin_garlic_num, half_cal_num);
            potato_num -= 1;
            green_pumpkin_num = Fraction.subFraction(green_pumpkin_num, half_cal_num);
            mushroom_num -= 100;
            tofu_num = Fraction.subFraction(tofu_num, half_cal_num);
            onion_num = Fraction.subFraction(onion_num, onion_cal_num);
            chungyang_pepper_num -= 2;
            green_onion_num = Fraction.subFraction(green_onion_num, green_onion_cal_num);

            rice_water.setText(rice_water_num+"");
            soybean_paste.setText(soybean_paste_num+"");
            if(dajin_garlic_num.getDenominator() == dajin_garlic_num.getNumerator()){
                dajin_garlic.setText(1+"");
            }
            else if(dajin_garlic_num.getDenominator() == 1)
                dajin_garlic.setText(dajin_garlic_num.getNumerator()+"");
            else
                dajin_garlic.setText(dajin_garlic_num.getNumerator() + "/" + dajin_garlic_num.getDenominator()+"");
            if(pepper_powder_num.getDenominator() == pepper_powder_num.getNumerator()){
                pepper_powder.setText(1+"");
            }
            else if(pepper_powder_num.getDenominator() == 1)
                pepper_powder.setText(pepper_powder_num.getNumerator()+"");
            else
                pepper_powder.setText(pepper_powder_num.getNumerator() + "/" + pepper_powder_num.getDenominator()+"");
            potato.setText(potato_num+"");
            if(green_pumpkin_num.getDenominator() == green_pumpkin_num.getNumerator()){
                green_pumpkin.setText(1+"");
            }
            else if(green_pumpkin_num.getDenominator() == 1)
                green_pumpkin.setText(green_pumpkin_num.getNumerator()+"");
            else
                green_pumpkin.setText(green_pumpkin_num.getNumerator() + "/" + green_pumpkin_num.getDenominator()+"");

            mushroom.setText(mushroom_num+"");

            if(tofu_num.getDenominator() == tofu_num.getNumerator()){
                tofu.setText(1+"");
            }
            else if(tofu_num.getDenominator() == 1)
                tofu.setText(tofu_num.getNumerator()+"");
            else
                tofu.setText(tofu_num.getNumerator() + "/" + tofu_num.getDenominator()+"");

            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");

            chungyang_pepper.setText(chungyang_pepper_num+"");
            if(green_onion_num.getDenominator() == green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");

        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), korea_Denchangstew2.class);
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