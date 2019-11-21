package com.example.assignment2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FolderDetailActivity extends AppCompatActivity {

    private String[] paths;
    private GridView gridView;
    private List<Map<String, Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folder_detail);
        gridView = (GridView) findViewById(R.id.imageGridView);
        Intent intent = getIntent();
        String value = intent.getStringExtra("path");
        String fullPath = FolderUtil.getFileRoot(FolderDetailActivity.this)+ File.separator +value;
        paths = FolderUtil.getAllFiles(fullPath);
        Log.i("1111",value);
        list = new ArrayList<Map<String, Object>>();
        gridView.post(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < paths.length; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("image", getImages(paths[i]));
                    list.add(map);

                }
                MyAdapter adapter = new MyAdapter();

                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        FolderUtil.saveImageToGallery(FolderDetailActivity.this,getImages(paths[position]));
                    }
                });
            }
        });


    }

    public Bitmap getImages(String currentPhotoPath){
        int targetW = gridView.getWidth()/3 - 10;
        int targetH = gridView.getWidth()/3 - 10;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                // 第一次加载创建View，其余复用 View
                convertView = LayoutInflater.from(FolderDetailActivity.this).inflate(
                        R.layout.image_grid_item, null);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView
                        .findViewById(R.id.grid_img);
                // 打标签
                convertView.setTag(holder);

            } else {
                // 从标签中获取数据
                holder = (ViewHolder) convertView.getTag();
            }

            // 根据key值设置不同数据内容
            holder.imageView.setImageBitmap((Bitmap) list.get(position).get(
                    "image"));

            return convertView;
        }
    }

    class ViewHolder {
        ImageView imageView;


    }
}
