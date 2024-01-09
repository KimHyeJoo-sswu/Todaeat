package com.sw.todaeat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class scrapfolderNamePopupActivity extends AppCompatActivity {

    private Button OK;
    private EditText newFolderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_folder_name_popup);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        OK=findViewById(R.id.ok);
        newFolderName=findViewById(R.id.newFolderName);


        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result= newFolderName.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("NewFolderName",result);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
