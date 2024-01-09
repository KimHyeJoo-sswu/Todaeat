package com.sw.todaeat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class scrapfolderListView_layout extends AppCompatActivity {

     TextView FolderName;
     Button deleteFolder;
     Button folderImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrap_folder_list_view_layout);

        FolderName=findViewById(R.id.folderName);
        deleteFolder=findViewById(R.id.deleteFolder);
        folderImage=findViewById(R.id.folderImage);

    }
}
