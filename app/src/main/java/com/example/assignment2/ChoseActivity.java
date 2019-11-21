package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ChoseActivity extends AppCompatActivity {
    private ListView choseListView;
    private String[] folderList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_folder);
        choseListView = (ListView)findViewById(R.id.choseFolder);
        folderList = FolderUtil.getAllFolders(getApplicationContext());
        adapter=new ArrayAdapter<String>(ChoseActivity.this,android.R.layout.simple_list_item_1,folderList);

        choseListView.setAdapter(adapter);

        choseListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent data = new Intent();
                data.putExtra("path",folderList[position]);
                setResult(RESULT_OK,data);
                finish();
            }
        });

    }

}
