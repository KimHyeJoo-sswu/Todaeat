package com.sw.todaeat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.shadow.ShadowRenderer;


public class WaterActivity extends AppCompatActivity {
    Toolbar myToolbar;
    ImageButton btn, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    TextView today;
    TextView drink;
    TextView tvCount;

    boolean i1 = true;
    boolean i2 = true;
    boolean i3 = true;
    boolean i4 = true;
    boolean i5 = true;
    boolean i6 = true;
    boolean i7 = true;
    boolean i8 = true;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        //Toolbar를 생성
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //추가된 소스코드, Toolbar의 왼쪽에 버튼을 추가하고 버튼의 아이콘을 바꾼다.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back2);

        getSupportActionBar().setTitle("");  //해당 액티비티의 툴바에 있는 타이틀을 공백으로 처리

        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        today = findViewById(R.id.today);
        drink = findViewById(R.id.drink);
        tvCount = findViewById(R.id.tvCount);
        tvCount.setText(count + "");

    }


    public void changeImage(View v){
        if (i1==true){
            btn.setImageResource(R.drawable.full);
            Toast.makeText(this, "물 한 잔 추가합니다.", Toast.LENGTH_SHORT).show();
            count=count+1;
            tvCount.setText(count+"");
            i1=false;
        }else if (i1==false){
            btn.setImageResource(R.drawable.empty);
            count=count-1;
            tvCount.setText(count+"");
            i1=true;
        }
    }
    public void changeImage2(View v){
        if (i2==true){
            btn2.setImageResource(R.drawable.full);
            count=count+1;
            tvCount.setText(count+"");
            Toast.makeText(this, "물 한 잔을 추가합니다.", Toast.LENGTH_SHORT).show();
            i2=false;
        }else if (i2==false){
            btn2.setImageResource(R.drawable.empty);
            count=count-1;
            tvCount.setText(count+"");
            i2=true;
        }
    }
    public void changeImage3(View v){
        if (i3==true){
            btn3.setImageResource(R.drawable.full);
            count=count+1;
            tvCount.setText(count+"");
            Toast.makeText(this, "물 한 잔을 추가합니다.", Toast.LENGTH_SHORT).show();
            i3=false;
        }else if (i3==false){
            btn3.setImageResource(R.drawable.empty);
            count=count-1;
            tvCount.setText(count+"");
            i3=true;
        }
    }
    public void changeImage4(View v){
        if (i4==true){
            btn4.setImageResource(R.drawable.full);
            count=count+1;
            tvCount.setText(count+"");
            Toast.makeText(this, "물 한 잔을 추가합니다.", Toast.LENGTH_SHORT).show();
            i4=false;
        }else if (i4==false){
            btn4.setImageResource(R.drawable.empty);
            count=count-1;
            tvCount.setText(count+"");
            i4=true;
        }
    }
    public void changeImage5(View v){
        if (i5==true){
            btn5.setImageResource(R.drawable.full);
            count=count+1;
            tvCount.setText(count+"");
            Toast.makeText(this, "물 한 잔을 추가합니다.", Toast.LENGTH_SHORT).show();
            i5=false;
        }else if (i5==false){
            btn5.setImageResource(R.drawable.empty);
            count=count-1;
            tvCount.setText(count+"");
            i5=true;
        }
    }
    public void changeImage6(View v){
        if (i6==true){
            btn6.setImageResource(R.drawable.full);
            count=count+1;
            tvCount.setText(count+"");
            Toast.makeText(this, "물 한 잔을 추가합니다.", Toast.LENGTH_SHORT).show();
            i6=false;
        }else if (i6==false){
            btn6.setImageResource(R.drawable.empty);
            count=count-1;
            tvCount.setText(count+"");
            i6=true;
        }
    }
    public void changeImage7(View v){
        if (i7==true){
            btn7.setImageResource(R.drawable.full);
            count=count+1;
            tvCount.setText(count+"");
            Toast.makeText(this, "물 한 잔을 추가합니다.", Toast.LENGTH_SHORT).show();
            i7=false;
        }else if (i7==false){
            btn7.setImageResource(R.drawable.empty);
            count=count-1;
            tvCount.setText(count+"");
            i7=true;
        }
    }
    public void changeImage8(View v){
        if (i8==true){
            btn8.setImageResource(R.drawable.full);
            count=count+1;
            tvCount.setText(count+"");
            Toast.makeText(this, "물 한 잔을 추가합니다.", Toast.LENGTH_SHORT).show();
            i8=false;
        }else if (i8==false){
            btn8.setImageResource(R.drawable.empty);
            count=count-1;
            tvCount.setText(count+"");
            i8=true;
        }
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수 (환경 설정)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            //toolbar 뒤로가는 부분
            case android.R.id.home:{
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);  // 눌렀을 때 메인화면으로 이동하기
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