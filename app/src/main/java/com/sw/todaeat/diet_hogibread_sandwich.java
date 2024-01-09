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


public class diet_hogibread_sandwich extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView hogibread, egg, ham, tomato, carrot, sangchu, wholegrain_mustard, chicken_breast, sriracha, mustard;
    private Fraction tomato_num = new Fraction(2);
    private Fraction carrot_num = new Fraction(2);
    private Fraction half_cal_num = new Fraction(2);
    private int hogibread_num = 1;
    private int egg_num = 1;
    private int ham_num = 3;
    private int sangchu_num = 3;
    private int wholegrain_mustard_num = 2;
    private int chicken_breast_num = 100;
    private int sriracha_num = 1;
    private int mustard_num = 1;

    boolean bookmark_pressed = false;
    Button bookmark;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.diet_food_3,"호기빵 샌드위치",15)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_hogibread_sandwich1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(diet_hogibread_sandwich.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.diet_food_3,"호기빵 샌드위치",15))){
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

        hogibread = findViewById(R.id.hogibread_num);
        egg = findViewById(R.id.egg_num);
        ham = findViewById(R.id.ham_num);
        tomato = findViewById(R.id.tomato_num);
        carrot = findViewById(R.id.carrot_num);
        sangchu = findViewById(R.id.sangchu_num);
        wholegrain_mustard = findViewById(R.id.wholegrain_mustard_num);
        chicken_breast = findViewById(R.id.chicken_breast_num);
        sriracha = findViewById(R.id.sriracha_num);
        mustard = findViewById(R.id.mustard_num);


        if(tomato_num.getDenominator() == tomato_num.getNumerator()){
            tomato.setText(1+"");
        }
        else if(tomato_num.getDenominator() == 1)
            tomato.setText(tomato_num.getNumerator()+"");
        else
            tomato.setText(tomato_num.getNumerator() + "/" + tomato_num.getDenominator()+"");

        if(carrot_num.getDenominator() == carrot_num.getNumerator()){
            carrot.setText(1+"");
        }
        else if(carrot_num.getDenominator() == 1)
            carrot.setText(carrot_num.getNumerator()+"");
        else
            carrot.setText(carrot_num.getNumerator() + "/" + carrot_num.getDenominator()+"");

        hogibread.setText(hogibread_num+"");
        egg.setText(egg_num+"");
        ham.setText(ham_num+"");
        sangchu.setText(sangchu_num+"");
        wholegrain_mustard.setText(wholegrain_mustard_num+"");
        chicken_breast.setText(chicken_breast_num+"");
        sriracha.setText(sriracha_num+"");
        mustard.setText(mustard_num+"");

        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            tomato_num = Fraction.addFraction(tomato_num, half_cal_num);
            carrot_num = Fraction.addFraction(carrot_num, half_cal_num);
            hogibread_num += 1;
            egg_num += 1;
            ham_num += 3;
            sangchu_num += 3;
            wholegrain_mustard_num += 2;
            chicken_breast_num += 100;
            sriracha_num += 1;
            mustard_num += 1;

            if(tomato_num.getDenominator() == tomato_num.getNumerator()){
                tomato.setText(1+"");
            }
            else if(tomato_num.getDenominator() == 1)
                tomato.setText(tomato_num.getNumerator()+"");
            else
                tomato.setText(tomato_num.getNumerator() + "/" + tomato_num.getDenominator()+"");

            if(carrot_num.getDenominator() == carrot_num.getNumerator()){
                carrot.setText(1+"");
            }
            else if(carrot_num.getDenominator() == 1)
                carrot.setText(carrot_num.getNumerator()+"");
            else
                carrot.setText(carrot_num.getNumerator() + "/" + carrot_num.getDenominator()+"");

            hogibread.setText(hogibread_num+"");
            egg.setText(egg_num+"");
            ham.setText(ham_num+"");
            sangchu.setText(sangchu_num+"");
            wholegrain_mustard.setText(wholegrain_mustard_num+"");
            chicken_breast.setText(chicken_breast_num+"");
            sriracha.setText(sriracha_num+"");
            mustard.setText(mustard_num+"");
        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            tomato_num = Fraction.subFraction(tomato_num, half_cal_num);
            carrot_num = Fraction.subFraction(carrot_num, half_cal_num);
            hogibread_num -= 1;
            egg_num -= 1;
            ham_num -= 3;
            sangchu_num -= 3;
            wholegrain_mustard_num -= 2;
            chicken_breast_num -= 100;
            sriracha_num -= 1;
            mustard_num -= 1;

            if(tomato_num.getDenominator() == tomato_num.getNumerator()){
                tomato.setText(1+"");
            }
            else if(tomato_num.getDenominator() == 1)
                tomato.setText(tomato_num.getNumerator()+"");
            else
                tomato.setText(tomato_num.getNumerator() + "/" + tomato_num.getDenominator()+"");

            if(carrot_num.getDenominator() == carrot_num.getNumerator()){
                carrot.setText(1+"");
            }
            else if(carrot_num.getDenominator() == 1)
                carrot.setText(carrot_num.getNumerator()+"");
            else
                carrot.setText(carrot_num.getNumerator() + "/" + carrot_num.getDenominator()+"");

            hogibread.setText(hogibread_num+"");
            egg.setText(egg_num+"");
            ham.setText(ham_num+"");
            sangchu.setText(sangchu_num+"");
            wholegrain_mustard.setText(wholegrain_mustard_num+"");
            chicken_breast.setText(chicken_breast_num+"");
            sriracha.setText(sriracha_num+"");
            mustard.setText(mustard_num+"");
        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_Hogisandwich2.class);
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