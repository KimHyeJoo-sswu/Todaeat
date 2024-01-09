package com.sw.todaeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity_diet extends AppCompatActivity {
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_diet);

        //delivery button(1)
        Button button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coupang.com"));
                startActivity(intent);
            }
        });

        //(2)
        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://emart.ssg.com"));
                startActivity(intent);
            }
        });

        //(3)
        Button button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://front.homeplus.co.kr"));
                startActivity(intent);
            }
        });

        //(4) 배민상회
        Button button4 = (Button) findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mart.baemin.com"));
                startActivity(intent);
            }
        });

        //(5) G마켓
        Button button5 = (Button) findViewById(R.id.button5);

        button5.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gmarket.com"));
                startActivity(intent);
            }
        });

        //(6) 마켓컬리
        Button button6 = (Button) findViewById(R.id.button6);

        button6.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kurly.com"));
                startActivity(intent);
            }
        });

        //(7) 옥션
        Button button7 = (Button) findViewById(R.id.button7);

        button7.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.acution.com"));
                startActivity(intent);
            }
        });

        //(8) 나우픽
        Button button8 = (Button) findViewById(R.id.button8);

        button8.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://nowpick.co.kr"));
                startActivity(intent);
            }
        });

        //(9) 위메프
        Button button9 = (Button) findViewById(R.id.button9);

        button9.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://front.wemakeprice.com"));
                startActivity(intent);
            }
        });

        // 해당 레시피로 이동 버튼
        ImageView image1 = findViewById(R.id.title_image_1);
        image1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_abocado_deopbap1.class);
                startActivity(intent);
            }
        });

        ImageView image2 = findViewById(R.id.title_image_2);
        image2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_paprika_pizza1.class);
                startActivity(intent);
            }
        });

        ImageView image3 = findViewById(R.id.title_image_3);
        image3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_hogibread_sandwich.class);
                startActivity(intent);
            }
        });

        ImageView image4 = findViewById(R.id.title_image_4);
        image4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_pumpkin_eggslut1.class);
                startActivity(intent);
            }
        });

        ImageView image5 = findViewById(R.id.title_image_5);
        image5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_chicken_breast_soysauce_bokkeumbap.class);
                startActivity(intent);
            }
        });

        ImageView image6 = findViewById(R.id.title_image_6);
        image6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_tuna_kitokimbap1.class);
                startActivity(intent);
            }
        });

        ImageView image7 = findViewById(R.id.title_image_7);
        image7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_oatmeal_maesaengi1.class);
                startActivity(intent);
            }
        });

        ImageView image8 = findViewById(R.id.title_image_8);
        image8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_tofu_chunggyungchae1.class);
                startActivity(intent);
            }
        });

        ImageView image9 = findViewById(R.id.title_image_9);
        image9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_chicken_breast_kimbap.class);
                startActivity(intent);
            }
        });

        ImageView image10 = findViewById(R.id.title_image_10);
        image10.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), diet_curry_bokkeumbap.class);
                startActivity(intent);
            }
        });

        //Toolbar를 생성
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //추가된 소스코드, Toolbar의 왼쪽에 버튼을 추가하고 버튼의 아이콘을 바꾼다.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back2);

        getSupportActionBar().setTitle("");  //해당 액티비티의 툴바에 있는 타이틀을 공백으로 처리


    }

    // 추가된 소스, ToolBar에 menu.xml을 인플레이트함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수 (환경 설정)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.search:
                // User chose the "Settings" item, show the app settings UI...
                final InputMethodManager manager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return true;

            case R.id.bookmark:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "스크랩 페이지로 이동", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), scrapPage.class);
                startActivity(intent1);
                return true;

            case R.id.camera:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "앨범으로 이동", Toast.LENGTH_LONG).show();
                return true;

            //toolbar 뒤로가는 부분
            case android.R.id.home:{
                Intent intent2 = new Intent(getApplicationContext(), Categoryfood.class);  // 눌렀을 때 메인화면으로 이동하기
                startActivity(intent2);

                return true;
            }

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }
    }

}
