package com.sw.todaeat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
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


public class diet_tofu_chunggyungchae1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView tofu, chungyang_pepper, onion, dajin_garlic, soybean_paste, sesemi_oil;

    private Fraction tofu_num = new Fraction(2);
    private int chungyang_pepper_num = 1;
    private Fraction onion_num = new Fraction(2);
    private Fraction dajin_garlic_num = new Fraction(2);
    private int soybean_paste_num = 2;
    private int sesemi_oil_num = 1;
    private Fraction half_cal_num = new Fraction(2);

    boolean bookmark_pressed = false;
    Button bookmark;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.diet_food_8,"두부 청경채 볶음",15)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_tofu_chunggyungchae1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(diet_tofu_chunggyungchae1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.diet_food_8,"두부 청경채 볶음",15))){
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

        tofu = findViewById(R.id.tofu_num);
        chungyang_pepper = findViewById(R.id.chungyang_pepper_num);
        onion = findViewById(R.id.onion_num);
        dajin_garlic = findViewById(R.id.dajin_garlic_num);
        soybean_paste = findViewById(R.id.soybean_paste_num);
        sesemi_oil = findViewById(R.id.sesemi_oil_num);


        if(tofu_num.getDenominator() == tofu_num.getNumerator()){
            tofu.setText(1+"");
        }
        else if(tofu_num.getDenominator() == 1)
            tofu.setText(tofu_num.getNumerator()+"");
        else
            tofu.setText(tofu_num.getNumerator() + "/" + tofu_num.getDenominator()+"");
        chungyang_pepper.setText(chungyang_pepper_num+"");
        if(onion_num.getDenominator() == onion_num.getNumerator()){
            onion.setText(1+"");
        }
        else if(onion_num.getDenominator() == 1)
            onion.setText(onion_num.getNumerator()+"");
        else
            onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");

        if(dajin_garlic_num.getDenominator() == dajin_garlic_num.getNumerator()){
            dajin_garlic.setText(1+"");
        }
        else if(dajin_garlic_num.getDenominator() == 1)
            dajin_garlic.setText(dajin_garlic_num.getNumerator()+"");
        else
            dajin_garlic.setText(dajin_garlic_num.getNumerator() + "/" + dajin_garlic_num.getDenominator()+"");
        soybean_paste.setText(soybean_paste_num+"");
        sesemi_oil.setText(sesemi_oil_num+"");

        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            tofu_num = Fraction.addFraction(tofu_num, half_cal_num);
            chungyang_pepper_num += 1;
            onion_num = Fraction.addFraction(onion_num, half_cal_num);
            dajin_garlic_num = Fraction.addFraction(dajin_garlic_num, half_cal_num);
            soybean_paste_num += 2;
            sesemi_oil_num += 1;

            if(tofu_num.getDenominator() == tofu_num.getNumerator()){
                tofu.setText(1+"");
            }
            else if(tofu_num.getDenominator() == 1)
                tofu.setText(tofu_num.getNumerator()+"");
            else
                tofu.setText(tofu_num.getNumerator() + "/" + tofu_num.getDenominator()+"");
            chungyang_pepper.setText(chungyang_pepper_num+"");
            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");

            if(dajin_garlic_num.getDenominator() == dajin_garlic_num.getNumerator()){
                dajin_garlic.setText(1+"");
            }
            else if(dajin_garlic_num.getDenominator() == 1)
                dajin_garlic.setText(dajin_garlic_num.getNumerator()+"");
            else
                dajin_garlic.setText(dajin_garlic_num.getNumerator() + "/" + dajin_garlic_num.getDenominator()+"");
            soybean_paste.setText(soybean_paste_num+"");
            sesemi_oil.setText(sesemi_oil_num+"");
        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            tofu_num = Fraction.subFraction(tofu_num, half_cal_num);
            chungyang_pepper_num -= 1;
            onion_num = Fraction.subFraction(onion_num, half_cal_num);
            dajin_garlic_num = Fraction.subFraction(dajin_garlic_num, half_cal_num);
            soybean_paste_num -= 2;
            sesemi_oil_num -= 1;


            if(tofu_num.getDenominator() == tofu_num.getNumerator()){
                tofu.setText(1+"");
            }
            else if(tofu_num.getDenominator() == 1)
                tofu.setText(tofu_num.getNumerator()+"");
            else
                tofu.setText(tofu_num.getNumerator() + "/" + tofu_num.getDenominator()+"");
            chungyang_pepper.setText(chungyang_pepper_num+"");
            if(onion_num.getDenominator() == onion_num.getNumerator()){
                onion.setText(1+"");
            }
            else if(onion_num.getDenominator() == 1)
                onion.setText(onion_num.getNumerator()+"");
            else
                onion.setText(onion_num.getNumerator() + "/" + onion_num.getDenominator()+"");

            if(dajin_garlic_num.getDenominator() == dajin_garlic_num.getNumerator()){
                dajin_garlic.setText(1+"");
            }
            else if(dajin_garlic_num.getDenominator() == 1)
                dajin_garlic.setText(dajin_garlic_num.getNumerator()+"");
            else
                dajin_garlic.setText(dajin_garlic_num.getNumerator() + "/" + dajin_garlic_num.getDenominator()+"");
            soybean_paste.setText(soybean_paste_num+"");
            sesemi_oil.setText(sesemi_oil_num+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_Tofu_chunggyungchae2.class);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity_diet.class);  // 눌렀을 때 메인화면으로 이동하기
                startActivity(intent);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}