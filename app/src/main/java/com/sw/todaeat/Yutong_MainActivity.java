package com.sw.todaeat;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Yutong_MainActivity extends AppCompatActivity implements View.OnClickListener, OnDatabaseCallback {
    private static final String TAG = "Yutong_MainActivity";

    ImageButton inputBtn;
    ImageButton backBtn;
    ImageButton marketBtn;
    TextView todayText;
    EditFragment inputFragment;
    ListFragment listFragment;
    EditFragment editFragment;

    ImageButton lotte;
    ImageButton home;
    ImageButton tm;
    ImageButton em;
    ImageButton cou;

    View editMenu;
    View listMenu;
    View marketTab;
    boolean isMarketTab = false;
    StuffDatabase database;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yutong);

        listMenu = findViewById(R.id.listMenu);

        editMenu = findViewById(R.id.editMenu);
        editMenu.setVisibility(View.GONE);



        todayText = findViewById(R.id.today);
        todayText.setText(getToday());



        inputBtn = (ImageButton) findViewById(R.id.inputBtn);
        inputBtn.setOnClickListener(this);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });



        //fragment 세팅
        inputFragment = new EditFragment();
        listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, listFragment).commit();


        //database 세팅
        if (database != null) {
            database.close();
            database = null;
        }

        database = StuffDatabase.getInstance(this);

        boolean isOpen = database.open();
        if (isOpen) {
            Log.d(TAG, "Stuff database is open.");
        } else {
            Log.d(TAG, "Stuff database is not open.");
        }
    }

    protected void onDestroy() {
        // close database
        if (database != null) {
            database.close();
            database = null;
        }
        super.onDestroy();
    }

    //오늘 날짜 설정 메소드
    public String getToday() {
        SimpleDateFormat today = new SimpleDateFormat("yyyy / MM / dd");
        return "Today is...\n" + today.format(new Date());
    }

    //리스트로 돌아가기 메소드
    public void backMain() {
        listMenu.setVisibility(View.VISIBLE);
        editMenu.setVisibility(View.GONE);
        //애니메이션 설정
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, listFragment);
        fragmentTransaction.commit();
    }

    //input 버튼 눌렀을 때
    public void openInput() {
        listMenu.setVisibility(View.GONE);
        editMenu.setVisibility(View.VISIBLE);
        inputFragment = new EditFragment();
        //애니메이션 설정
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, inputFragment);
        fragmentTransaction.commit();

    }

    //리스트에서 아이템 선택했을 때.
    public void openEdit(String name, String date) {
        listMenu.setVisibility(View.GONE);
        editMenu.setVisibility(View.VISIBLE);

        editFragment = new EditFragment(name, date);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, editFragment);
        fragmentTransaction.commit();
    }

    //마켓탭 열기
    public void openMarketTab(){
        if(isMarketTab==true){
            isMarketTab = false;
            marketTab.setVisibility(View.GONE);
        }
        else {
            isMarketTab = true;
            marketTab.setVisibility(View.VISIBLE);
            marketTab.bringToFront();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.inputBtn:
                openInput();
                break;
            case R.id.backBtn:
                backMain();
                break;

        }
    }

    @Override
    public void insert(String name, String date) {
        database.insertRecord(name, date);
        Toast.makeText(getApplicationContext(), name + "을 추가했습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void update(String oldName, String name, String date) {
        database.updateRecord(oldName, name, date);
        Toast.makeText(getApplicationContext(), name + "을 수정했습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void delete(String name) {
        database.deleteRecord(name);
        Toast.makeText(getApplicationContext(), name + "을 삭제했습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public String search(String name) {
        String r;
        r = database.searchRecord(name);
        return r;
    }

    @Override
    public ArrayList<StuffInfo> selectAll() {
        ArrayList<StuffInfo> result = database.selectAll();

        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backMain();
    }
}