
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


public class diet_oatmeal_maesaengi1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView maesaengi, oatmeal, egg, soy_sauce, sesemi_oil;
    private int maesaengi_num = 1;
    private int oatmeal_num = 30;
    private int egg_num = 1;
    private int soy_sauce_num = 2;
    private Fraction sesemi_oil_num = new Fraction(2);
    private Fraction sesemi_oil_cal_num = new Fraction(2);

    boolean bookmark_pressed = false;
    Button bookmark;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.diet_food_7,"오트밀 매생이죽",10)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_oatmeal_maesaengi1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(diet_oatmeal_maesaengi1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.diet_food_7,"오트밀 매생이죽",10))){
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

        maesaengi = findViewById(R.id.maesaengi_num);
        oatmeal = findViewById(R.id.oatmeal_num);
        egg = findViewById(R.id.egg_num);
        soy_sauce = findViewById(R.id.soy_sauce_num);
        sesemi_oil = findViewById(R.id.sesemi_oil_num);


        maesaengi.setText(maesaengi_num+"");
        oatmeal.setText(oatmeal_num+"");
        egg.setText(egg_num+"");
        soy_sauce.setText(soy_sauce_num+"");
        if(sesemi_oil_num.getDenominator() == sesemi_oil_num.getNumerator()){
            sesemi_oil.setText(1+"");
        }
        else if(sesemi_oil_num.getDenominator() == 1)
            sesemi_oil.setText(sesemi_oil_num.getNumerator()+"");
        else
            sesemi_oil.setText(sesemi_oil_num.getNumerator() + "/" + sesemi_oil_num.getDenominator()+"");


        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            maesaengi_num += 1;
            oatmeal_num += 30;
            egg_num += 1;
            soy_sauce_num += 2;
            sesemi_oil_num = Fraction.addFraction(sesemi_oil_num, sesemi_oil_cal_num);

            maesaengi.setText(maesaengi_num+"");
            oatmeal.setText(oatmeal_num+"");
            egg.setText(egg_num+"");
            soy_sauce.setText(soy_sauce_num+"");
            if(sesemi_oil_num.getDenominator() == sesemi_oil_num.getNumerator()){
                sesemi_oil.setText(1+"");
            }
            else if(sesemi_oil_num.getDenominator() == 1)
                sesemi_oil.setText(sesemi_oil_num.getNumerator()+"");
            else
                sesemi_oil.setText(sesemi_oil_num.getNumerator() + "/" + sesemi_oil_num.getDenominator()+"");
        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            maesaengi_num -= 1;
            oatmeal_num -= 30;
            egg_num -= 1;
            soy_sauce_num -= 2;
            sesemi_oil_num = Fraction.subFraction(sesemi_oil_num, sesemi_oil_cal_num);

            maesaengi.setText(maesaengi_num+"");
            oatmeal.setText(oatmeal_num+"");
            egg.setText(egg_num+"");
            soy_sauce.setText(soy_sauce_num+"");
            if(sesemi_oil_num.getDenominator() == sesemi_oil_num.getNumerator()){
                sesemi_oil.setText(1+"");
            }
            else if(sesemi_oil_num.getDenominator() == 1)
                sesemi_oil.setText(sesemi_oil_num.getNumerator()+"");
            else
                sesemi_oil.setText(sesemi_oil_num.getNumerator() + "/" + sesemi_oil_num.getDenominator()+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_OatmealMeseng2.class);
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