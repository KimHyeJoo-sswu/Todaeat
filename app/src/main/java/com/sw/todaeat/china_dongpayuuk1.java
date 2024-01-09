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

public class china_dongpayuuk1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView pork,onion, soy_sauce, oyster_sauce, sugar;
    private int pork_num = 200;
    private int onion_num = 1;
    private int soy_sauce_num = 2;
    private int oyster_sauce_num = 2;
    private int sugar_num = 2;

    boolean bookmark_pressed = false;
    Button bookmark;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.china_food_2_dongpayuuk,"동파육",60)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.china_dongpayuuk_1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(china_dongpayuuk1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.china_food_2_dongpayuuk,"동파육",60))){
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
        soy_sauce = findViewById(R.id.soy_sauce_num);
        oyster_sauce = findViewById(R.id.oyster_sauce_num);
        sugar = findViewById(R.id.sugar_num);

        pork.setText(pork_num+"");
        onion.setText(onion_num+"");
        soy_sauce.setText(soy_sauce_num+"");
        oyster_sauce.setText(oyster_sauce_num+"");
        sugar.setText(sugar_num+"");


        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            pork_num += 200;
            onion_num += 1;
            soy_sauce_num += 2;
            oyster_sauce_num += 2;
            sugar_num += 2;

            pork.setText(pork_num+"");
            onion.setText(onion_num+"");
            soy_sauce.setText(soy_sauce_num+"");
            oyster_sauce.setText(oyster_sauce_num+"");
            sugar.setText(sugar_num+"");

        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            pork_num -= 200;
            onion_num -= 1;
            soy_sauce_num -= 2;
            oyster_sauce_num -= 2;
            sugar_num -= 2;

            pork.setText(pork_num+"");
            onion.setText(onion_num+"");
            soy_sauce.setText(soy_sauce_num+"");
            oyster_sauce.setText(oyster_sauce_num+"");
            sugar.setText(sugar_num+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), china_Dongpayuuk2.class);
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