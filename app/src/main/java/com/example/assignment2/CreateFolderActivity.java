package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateFolderActivity extends AppCompatActivity {
    private Button addFolderButton;
    private TextView folderNameEditView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        addFolderButton = findViewById(R.id.addFolderButton);
        folderNameEditView=findViewById(R.id.folderNameEditView);
        addFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("path",folderNameEditView.getText().toString());
                setResult(RESULT_OK,data);
                finish();
            }
        });
    }
}
