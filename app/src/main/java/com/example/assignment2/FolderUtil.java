package com.example.assignment2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FolderUtil {

    public static boolean generateFolder(Context context,String name){
        String root = getFileRoot(context);
        if (name.contains(File.separator)){
            return false;
        }
        File dir = new File(root + File.separator + name);
        if (dir.exists()){
            return false;
        }
        dir.mkdirs();

        return true;
    }

    public static String[] getAllFolders(Context context){
        String root = getFileRoot(context);
        File file=new File(root);
        File[] files=file.listFiles();
        if (files == null){return new String[0];}
        List<String> s = new ArrayList<>();
        for(int i =0;i<files.length;i++){
            if(files[i].isDirectory()){
                s.add(files[i].getName());
            }
        }
        String[] folders = new String[s.size()];
        folders = s.toArray(folders);
        return folders;

    }

    public static String[] getAllFiles(String path){

        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){return new String[0];}
        List<String> s = new ArrayList<>();
        for(int i =0;i<files.length;i++){
            s.add(files[i].getAbsolutePath());
        }
        String[] folders = new String[s.size()];
        folders = s.toArray(folders);
        return folders;

    }

    public static String getFileRoot(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getExternalFilesDir(null);
            if (external != null) {
                return external.getAbsolutePath();
            }
        }

        return context.getFilesDir().getAbsolutePath();
    }

    public static boolean saveImageToGallery(Context context, Bitmap bmp) {
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "QR";
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);

            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();

            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
