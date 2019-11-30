package com.example.assignment2.ui.generate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.assignment2.ChoseActivity;
import com.example.assignment2.FolderUtil;
import com.example.assignment2.R;
import com.yzq.zxinglibrary.encode.CodeCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class GenerateFragment extends Fragment {

    private GenerateViewModel generateViewModel;
    private ImageView qrImageView;
    private TextView qrTextView;
    private Button generateButton;
    private Button saveButton;
    private Bitmap bitmap;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        generateViewModel =
                ViewModelProviders.of(this).get(GenerateViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        qrImageView = root.findViewById(R.id.qrImageView);
        qrTextView =  root.findViewById(R.id.inputEditText);
        generateButton = root.findViewById(R.id.generateButton);
        saveButton = root.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap!=null){
                    Intent intent = new Intent(getActivity(), ChoseActivity.class);
                    startActivityForResult(intent,1);
                }


            }
        });
        generateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bitmap = CodeCreator.createQRCode(qrTextView.getText().toString(), 400, 400, null);
                qrImageView.setImageBitmap(bitmap);

            }
        });


        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String path = data.getStringExtra("path");
            final String fullPath = FolderUtil.getFileRoot(getActivity())+File.separator+path+File.separator+System.currentTimeMillis()+".jpg";
            File file = new File(fullPath);
            try {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}