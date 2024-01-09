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

public class sns_pencake_cereal1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1; // 음식 양(1인분)
    private TextView hotcake_powder, milk, egg; // 숫자 변경 필요한 음식재료들 이름
    private int hotcake_powder_num = 2;  // 각 재료들 현재 숫자
    private int milk_num = 2;
    private int egg_num = 1;

    Button bookmark;
    boolean bookmark_pressed = false;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.pencake_cereal,"팬케이크 시리얼",10)));
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sns_pencake_cereal1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(sns_pencake_cereal1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.pencake_cereal,"팬케이크 시리얼",10))){
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
        hotcake_powder = findViewById(R.id.pencake_powder_num);
        milk = findViewById(R.id.milk_num);
        egg = findViewById(R.id.egg_num);

        // 각 재료들 텍스트 셋팅
        hotcake_powder.setText(hotcake_powder_num+"");
        milk.setText(milk_num+"");
        egg.setText(egg_num+"");


        // 증가감소 버튼 id 가져오기
        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        // 증가버튼
        btnAdd.setOnClickListener(v -> {
            count++; // count값 1씩 증가
            tvCount.setText(count+""); // 그 증가한 값으로 count값 세팅

            // 각 숫자들 1씩 증가시켜주기
            hotcake_powder_num += 2;
            milk_num += 2;
            egg_num += 1;

            hotcake_powder.setText(hotcake_powder_num+"");
            milk.setText(milk_num+"");
            egg.setText(egg_num+"");
        });


        // 뺄셈 연산 외에는 btnAdd랑 과정 동일!
        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            hotcake_powder_num -= 2;
            milk_num -= 2;
            egg_num -= 1;

            hotcake_powder.setText(hotcake_powder_num+"");
            milk.setText(milk_num+"");
            egg.setText(egg_num+"");

        });
        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), sns_PencakeCereal2.class);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity_sns.class);  // 눌렀을 때 메인화면으로 이동하기
                startActivity(intent);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}