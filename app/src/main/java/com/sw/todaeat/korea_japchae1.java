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


public class korea_japchae1 extends AppCompatActivity {

    private Button btnAdd, btnMinus;
    private TextView tvCount;
    private int count = 1;
    private TextView dangmyeon, onion, carrot, fish_cake, mushroom, buchu;
    private int dangmyeon_num = 300;
    private int onion_num = 1;
    private Fraction carrot_num = new Fraction(2);
    private int fish_cake_num = 2;
    private int mushroom_num = 2;
    private int buchu_num = 40;
    private Fraction half_cal_num = new Fraction(2);

    Button bookmark;
    boolean bookmark_pressed = false;

    androidx.activity.result.ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String folder = data.getStringExtra("FolderName");
                scrapPage.scrapped.add(new scrapFoodListByFolderName(new scrapfolderNames(folder),new scrapFoods(R.drawable.korea_food_5_japchae,"잡채",30)));
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korea_japchae1);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe");

        bookmark = (Button)findViewById(R.id.bookmark);
        bookmark.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bookmark_pressed){

                    Intent intent = new Intent(korea_japchae1.this, scrapfolderName.class);
                    ActivityResultLauncher.launch(intent);

                    bookmark.setBackgroundResource(R.drawable.ic_bookmark);
                    bookmark_pressed = true;
                }
                else{
                    for(scrapFoodListByFolderName item : scrapPage.scrapped){
                        if(item.scrapFood.equals(new scrapFoods(R.drawable.korea_food_5_japchae,"잡채",30))){
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

        dangmyeon = findViewById(R.id.dangmyeon_num);
        onion = findViewById(R.id.onion_num);
        carrot = findViewById(R.id.carrot_num);
        fish_cake = findViewById(R.id.fish_cake_num);
        mushroom = findViewById(R.id.mushroom_num);
        buchu = findViewById(R.id.buchu_num);


        dangmyeon.setText(dangmyeon_num+"");
        onion.setText(onion_num+"");
        if(carrot_num.getDenominator() == carrot_num.getNumerator()){
            carrot.setText(1+"");
        }
        else if(carrot_num.getDenominator() == 1)
            carrot.setText(carrot_num.getNumerator()+"");
        else
            carrot.setText(carrot_num.getNumerator() + "/" + carrot_num.getDenominator()+"");
        fish_cake.setText(fish_cake_num+"");
        mushroom.setText(mushroom_num+"");
        buchu.setText(buchu_num+"");


        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);

        btnAdd.setOnClickListener(v -> {
            count++;
            tvCount.setText(count+"");

            dangmyeon_num += 300;
            onion_num += 1;
            carrot_num = Fraction.addFraction(carrot_num, half_cal_num);
            fish_cake_num += 2;
            mushroom_num += 2;
            buchu_num += 40;

            dangmyeon.setText(dangmyeon_num+"");
            onion.setText(onion_num+"");
            if(carrot_num.getDenominator() == carrot_num.getNumerator()){
                carrot.setText(1+"");
            }
            else if(carrot_num.getDenominator() == 1)
                carrot.setText(carrot_num.getNumerator()+"");
            else
                carrot.setText(carrot_num.getNumerator() + "/" + carrot_num.getDenominator()+"");
            fish_cake.setText(fish_cake_num+"");
            mushroom.setText(mushroom_num+"");
            buchu.setText(buchu_num+"");

        });


        btnMinus.setOnClickListener(v -> {
            count--;
            tvCount.setText(count+"");

            dangmyeon_num -= 300;
            onion_num -= 1;
            carrot_num = Fraction.subFraction(carrot_num, half_cal_num);
            fish_cake_num -= 2;
            mushroom_num -= 2;
            buchu_num -= 40;

            dangmyeon.setText(dangmyeon_num+"");
            onion.setText(onion_num+"");
            if(carrot_num.getDenominator() == carrot_num.getNumerator()){
                carrot.setText(1+"");
            }
            else if(carrot_num.getDenominator() == 1)
                carrot.setText(carrot_num.getNumerator()+"");
            else
                carrot.setText(carrot_num.getNumerator() + "/" + carrot_num.getDenominator()+"");
            fish_cake.setText(fish_cake_num+"");
            mushroom.setText(mushroom_num+"");
            buchu.setText(buchu_num+"");

        });

        Button adopt = findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), korea_Japchae2.class);
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