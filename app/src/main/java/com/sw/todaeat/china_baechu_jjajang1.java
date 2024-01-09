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

public class china_baechu_jjajang1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView al_cabbage, rice, green_onion, egg, dajin_garlic;
    private int al_cabbage_num = 4;
    private int rice_num = 1;
    private int green_onion_num = 1;
    private int egg_num = 1;
    private Fraction add_num = new Fraction(2);
    private Fraction dajin_garlic_num = new Fraction(2);

    boolean bookmark_pressed = false;
    Button bookmark;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.china_food_1_baechu_jjajang,"배추짜장덮밥",25)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.china_baechu_jjajang_1); // activity_~ 파일에서 텍스트 가져오기

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(china_baechu_jjajang1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.china_food_1_baechu_jjajang,"배추짜장덮밥",25))){
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

        al_cabbage = findViewById(R.id.al_cabbage_num);
        rice = findViewById(R.id.rice_num);
        green_onion = findViewById(R.id.green_onion_num);
        egg = findViewById(R.id.egg_num);
        dajin_garlic = findViewById(R.id.dajin_garlic_num);

        al_cabbage.setText(al_cabbage_num+"");
        rice.setText(rice_num+"");
        green_onion.setText(green_onion_num+"");
        egg.setText(egg_num+"");

        if(dajin_garlic_num.getDenominator() == dajin_garlic_num.getNumerator()){
            dajin_garlic.setText(1+"");
        }
        else if(dajin_garlic_num.getDenominator() == 1)
            dajin_garlic.setText(dajin_garlic_num.getNumerator()+"");
        else
            dajin_garlic.setText(dajin_garlic_num.getNumerator() + "/" + dajin_garlic_num.getDenominator()+"");


        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            al_cabbage_num += 4;
            rice_num += 1;
            green_onion_num += 1;
            egg_num += 1;

            dajin_garlic_num = Fraction.addFraction(dajin_garlic_num, add_num);

            al_cabbage.setText(al_cabbage_num+"");
            rice.setText(rice_num+"");
            green_onion.setText(green_onion_num+"");
            egg.setText(egg_num+"");

            if(dajin_garlic_num.getDenominator() == dajin_garlic_num.getNumerator()){
                dajin_garlic.setText(1+"");
            }
            else if(dajin_garlic_num.getDenominator() == 1)
                dajin_garlic.setText(dajin_garlic_num.getNumerator()+"");
            else
                dajin_garlic.setText(dajin_garlic_num.getNumerator() + "/" + dajin_garlic_num.getDenominator()+"");
        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            al_cabbage_num -= 4;
            rice_num -= 1;
            green_onion_num -= 1;
            egg_num -= 1;

            dajin_garlic_num = Fraction.subFraction(dajin_garlic_num, add_num);

            al_cabbage.setText(al_cabbage_num+"");
            rice.setText(rice_num+"");
            green_onion.setText(green_onion_num+"");
            egg.setText(egg_num+"");

            if(dajin_garlic_num.getDenominator() == dajin_garlic_num.getNumerator()){
                dajin_garlic.setText(1+"");
            }
            else if(dajin_garlic_num.getDenominator() == 1)
                dajin_garlic.setText(dajin_garlic_num.getNumerator()+"");
            else
                dajin_garlic.setText(dajin_garlic_num.getNumerator() + "/" + dajin_garlic_num.getDenominator()+"");

        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), china_baechu_jjajang2.class);
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