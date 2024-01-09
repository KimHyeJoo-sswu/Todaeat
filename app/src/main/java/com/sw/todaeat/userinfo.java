package com.sw.todaeat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// 회원정보 자바 파일, 다시 네비게이션 메뉴로 돌아가기

public class userinfo extends AppCompatActivity {

    EditText editText;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);



    }// onCreate()..

    public void clickSetBt(View view) {
        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(this, "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("inputText", editText.getText().toString());
            editor.commit();
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();

        }
    }// clickSetBt()..

    public void clickGetBt(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        String inputText = sharedPreferences.getString("inputText", "");
        textView.setText(inputText);
        Toast.makeText(this, "불러오기 하였습니다..", Toast.LENGTH_SHORT).show();
    }// clickGetBt()..

    public void clickmoveBt(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }



}

