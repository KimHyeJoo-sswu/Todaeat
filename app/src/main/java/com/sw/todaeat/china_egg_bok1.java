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

public class china_egg_bok1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1; // 음식 양(1인분)
    private TextView rice, egg, green_onion; // 숫자 변경 필요한 음식재료들 이름
    private int rice_num = 1;  // 각 재료들 현재 숫자
    private int egg_num = 1;
    private Fraction add_num = new Fraction(2); // 변경될 분수 숫자에서 더하거나 빼져야할 숫자. (만약 2/3컵에서 변경 필요하면 new Fraction(2,3) )
    private Fraction green_onion_num = new Fraction(2);

    boolean bookmark_pressed = false;
    Button bookmark;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.china_food_3_egg_bokkeumbap,"계란볶음밥",25)));
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.china_egg_bokkeumbap_1); // activity_~ 파일에서 텍스트 가져오기

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(china_egg_bok1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.china_food_3_egg_bokkeumbap,"계란볶음밥",25))){
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

        // 각 재료들 숫자 불러오기
        rice = findViewById(R.id.rice_num);
        egg = findViewById(R.id.egg_num);
        green_onion = findViewById(R.id.green_onion_num);

        // 각 재료들 텍스트 셋팅
        rice.setText(rice_num+"");
        egg.setText(egg_num+"");
        if(green_onion_num.getDenominator() ==green_onion_num.getNumerator()){
            green_onion.setText(1+"");
        }
        else if(green_onion_num.getDenominator() == 1)
            green_onion.setText(green_onion_num.getNumerator()+"");
        else
            green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");


        // 증가감소 버튼 id 가져오기
        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        // 증가버튼
        btnAdd.setOnClickListener(v -> {
            count++; // count값 1씩 증가
            tvCount.setText(count+""); // 그 증가한 값으로 count값 세팅

            // 각 숫자들 1씩 증가시켜주기
            rice_num += 1;
            egg_num += 1;
            // 분수인 경우 addFraction 함수 이용해서 계산하기 Fraction.addFraction(식재료 숫자, 더해지는 수)
            green_onion_num = Fraction.addFraction(green_onion_num, add_num);


            // 변경한 값으로 텍스트 다시 셋팅
            rice.setText(rice_num+"");
            egg.setText(egg_num+"");
            if(green_onion_num.getDenominator() ==green_onion_num.getNumerator()){
                green_onion.setText(1+"");
            }
            else if(green_onion_num.getDenominator() == 1)
                green_onion.setText(green_onion_num.getNumerator()+"");
            else
                green_onion.setText(green_onion_num.getNumerator() + "/" + green_onion_num.getDenominator()+"");
        });


        // 뺄셈 연산 외에는 btnAdd랑 과정 동일!
        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            rice_num -= 1;
            egg_num -= 1;
            green_onion_num = Fraction.subFraction(green_onion_num, add_num);

            rice.setText(rice_num+"");
            egg.setText(egg_num+"");
            if(green_onion_num.getDenominator() ==green_onion_num.getNumerator()){
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
                Intent intent = new Intent(getApplicationContext(), china_Eggbok2.class);
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