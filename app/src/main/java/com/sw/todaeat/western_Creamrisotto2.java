package com.sw.todaeat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class western_Creamrisotto2 extends AppCompatActivity {

    Toolbar myToolbar;

    public void openActivity() {
        Intent intent = new Intent(this, timer.class);
        startActivity(intent);
    }

    ChipNavigationBar chipNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.western_creamrizoddo_2);

        //요리 끝 버튼 누르면 -> 메인으로 이동
        Button adopt =(Button) findViewById(R.id.adopt);
        adopt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        chipNavigationBar=findViewById(R.id.nav_bar);

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                if(i==R.id.bottom_nav_timer){
                    openActivity();
                    findViewById(R.id.bottom_nav_timer).setSelected(false);
                }
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
    //추가된 소스, ToolBar에 menu.xml을 인플레이트함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_recipe, menu);
        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수 (환경 설정)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:{
                Intent intent2 = new Intent(getApplicationContext(), western_cream_risotto_1.class);  // 눌렀을 때 메인화면으로 이동하기
                startActivity(intent2);

                return true;
            }
            case R.id.calendar:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "달력 버튼 클릭됨", Toast.LENGTH_LONG).show();
                Intent intent_c = new Intent(getApplicationContext(), calendar.class);
                startActivity(intent_c);
                return true;

            case R.id.share:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "공유로 이동", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_SEND);

                //일단 공유할 때 텍스트가 전송됨. 앱 주소 받고 싶으면 -> 파이어베이스 연동하면 됨
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "오늘 사와 ~");
                intent.putExtra(Intent.EXTRA_TEXT,"장봐야할 재료를 공유해보세요!");
                startActivity(Intent.createChooser(intent, "Share Via"));
                return super.onOptionsItemSelected(item);

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }
    }
}