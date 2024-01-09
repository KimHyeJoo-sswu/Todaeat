package com.sw.todaeat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.OnClickListener;
import static android.view.View.VISIBLE;

public class scrapPage extends AppCompatActivity {

    private Button back;
    private Button home;
    private Button addFolder;
    private RecyclerView folderListRecycyclerView;
    public static TextView info1;
    public static TextView info2;
    private scrapRecyclerViewAdapter adapter;
    public static scrapDBOpenHelper mDBOpenHelper;
    public static ArrayList<scrapFoodListByFolderName> scrapped=new ArrayList<scrapFoodListByFolderName>();

    //addFolder 할 때 팝업창에서 폴더이름 입력받아서 새 폴더이름 설정
    ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String newName = data.getStringExtra("NewFolderName");
                adapter.addItem(newName);
                adapter.notifyDataSetChanged();
            }
        }
    });

    //addFolder 할 때 팝업창 띄우는 거
    public void openActivityForResult() {
        Intent intent = new Intent(this, scrapfolderNamePopupActivity.class);
        ActivityResultLauncher.launch(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrap);

        //각 항목 정의
        back=(Button) findViewById(R.id.back);
        home=(Button) findViewById(R.id.home);
        addFolder = (Button) findViewById(R.id.addFolder);
        folderListRecycyclerView = (RecyclerView) findViewById(R.id.folderListRecyclerView);
        info1 = (TextView) findViewById(R.id.hide1);
        info2 = (TextView) findViewById(R.id.hide2);

        mDBOpenHelper=new scrapDBOpenHelper(this);
        mDBOpenHelper.open();
        mDBOpenHelper.create();

        adapter = new scrapRecyclerViewAdapter(scrapPage.this);
        folderListRecycyclerView.setAdapter(adapter);
        //한 줄에 폴더 3개씩
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        folderListRecycyclerView.setLayoutManager(gridLayoutManager);

        Cursor iCursor=scrapPage.mDBOpenHelper.selectColumns();
        while(iCursor.moveToNext()){
            String folderName=iCursor.getString(iCursor.getColumnIndex("folderName"));
            boolean exist=false;
            for(scrapfolderNames item:adapter.folderNamesArrayList){
                String folderNameInList=item.getFolderName();
                if(folderNameInList.equals(folderName)){
                    exist=true;
                }
            }
            if(exist==false){
                adapter.addItem(folderName);
                folderListRecycyclerView.setVisibility(VISIBLE);
                info1.setVisibility(INVISIBLE);
                info2.setVisibility(INVISIBLE);
            }
        }

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //홈으로가기
            }
        });

        addFolder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                folderListRecycyclerView.setVisibility(VISIBLE);
                info1.setVisibility(INVISIBLE);
                info2.setVisibility(INVISIBLE);
                openActivityForResult();
            }
        });
    }

}