package com.example.saveopenimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imageView ;
    static int IMAGE_REQUEST_CODE = 100;
    static int PERMISSION_READ_REQUEST_CODE = 101;
    static int PERMISSION_WRITE_REQUEST_CODE = 102;
    FileOutputStream outputStream;
    private FileOutputStream outputStream1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }

    public void pickAPicture(View view) {
        if(!CheckPermisstion(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED)){
            askPermission(Manifest.permission.READ_EXTERNAL_STORAGE, PERMISSION_READ_REQUEST_CODE);
        }else{
            openGallery();
        }


    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }
    public void saveImage(View view) {
        if(!CheckPermisstion(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED)){
            askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, PERMISSION_WRITE_REQUEST_CODE);
        }else{
            File dir = new File(Environment.getExternalStorageDirectory(), "save-image");
            if(!dir.exists())
                dir.mkdir();
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            File file = new File(dir, System.currentTimeMillis() +".jpg");
            try {
                outputStream = new FileOutputStream(file);

            }catch (Exception e){
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            Toast.makeText(this, "DA LUU", Toast.LENGTH_SHORT).show();
            try {
                outputStream.flush();
                outputStream.close();

            }catch (Exception e){
                e.printStackTrace();
            }

        }



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_READ_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                openGallery();
            }else{
                Toast.makeText(this, "you must allow permission read storage", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == IMAGE_REQUEST_CODE && resultCode==RESULT_OK){
            imageView.setImageURI(data.getData());
            Toast.makeText(this, data.getData().toString(), Toast.LENGTH_SHORT).show();
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
    private void askPermission(String permission, int requestCode) {
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {permission} , requestCode);
    }
    public boolean CheckPermisstion(Context context, String permission, int granted){
        return ContextCompat.checkSelfPermission(context, permission) == granted;
    }

    public void getImage(View view) {
        File dir = new File(Environment.getExternalStorageDirectory(), "save-image");
        File image = new File(dir, "hieu.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(image.getPath());
        imageView.setImageBitmap(bitmap);
    }
}