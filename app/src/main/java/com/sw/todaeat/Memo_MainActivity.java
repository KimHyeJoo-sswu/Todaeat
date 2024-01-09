package com.sw.todaeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import android.widget.EditText;

public class Memo_MainActivity extends AppCompatActivity {

    //추가된 소스
    Toolbar myToolbar;

    //버튼에서 추가된
    EditText mMemoEdit = null;
    TextFileManger mTextFileManger = new TextFileManger(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        //
        mMemoEdit = (EditText)findViewById(R.id.memo_edit);

        //Toolbar를 생성
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //추가된 소스코드, Toolbar의 왼쪽에 버튼을 추가하고 버튼의 아이콘을 바꾼다.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        getSupportActionBar().setTitle("");  //해당 액티비티의 툴바에 있는 타이틀을 공백으로 처리
    }

    //++
    public void onClick(View v){
        switch (v.getId()){
            //1.파일에 저장된 메모 텍스트 파일 불러오기
            case R.id.load_btn:{
                String memoData = mTextFileManger.load();
                mMemoEdit.setText(memoData);

                Toast.makeText(this,"불러오기 완료", Toast.LENGTH_LONG).show();
                break;
            }
            //2.EditText에 입력된 메모를 텍스트 파일로 저장하기
            case R.id.save_btn:{
                String memoData = mMemoEdit.getText().toString();
                mTextFileManger.save(memoData);
                mMemoEdit.setText("");

                Toast.makeText(this,"저장완료", Toast.LENGTH_LONG).show();
                break;
            }
            //3.저장된 메모 파일 삭제
            case R.id.delete_btn:{
                mTextFileManger.delete();
                mMemoEdit.setText("");

                Toast.makeText(this,"삭제 완료", Toast.LENGTH_LONG).show();
            }

        }
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
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);  // 눌렀을 때 메인화면으로 이동하기
                startActivity(intent2);

                return true;
            }
            case R.id.calendar:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "달력 버튼 클릭됨", Toast.LENGTH_LONG).show();
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