package com.example.ontap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText etTen;
    private EditText etTuoi;
    private ListView listView;
    private static int IMAGE_CODE = 100;
    private static int READ_PERMISSION_CODE = 101;
    private static int WRITE_PERMISSION_CODE = 102;
    private Uri uri;
    private List<Person> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        etTen   = findViewById(R.id.etTen);
        etTuoi = findViewById(R.id.etTuoi);
        list = new ArrayList<Person>();
        listView = findViewById(R.id.listPerson);



    }

    public void chonAnh(View view) {
        if(!(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE }, READ_PERMISSION_CODE);
        }else{
            openGallery();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {



        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == READ_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }else{
                Toast.makeText(this, "ban can cap quyen doc file", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == WRITE_PERMISSION_CODE){

        }
    }

    public void askPermission(){

    }

    public void openGallery(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageView.setImageURI(data.getData());
        uri = data.getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void luuFile(){
        if(!(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE }, WRITE_PERMISSION_CODE);
        }else{
            File folder = new File(Environment.getExternalStorageDirectory(), "save-list-person");
            if(!folder.exists())
                folder.mkdir();
//            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
//            Bitmap bitmap = drawable.getBitmap();
            File file = new File(folder, "list-person.json");
            Gson gson = new Gson();
            String json = gson.toJson(list);
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(json.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Toast.makeText(this, "da luu", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.luu:{
                Toast.makeText(this, "luu ...", Toast.LENGTH_SHORT).show();
                luuFile();

                break;
            }
            case R.id.doc:{
                Toast.makeText(this, "doc ...", Toast.LENGTH_SHORT).show();
                docFile();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void docFile() {
        File folder = new File(Environment.getExternalStorageDirectory(), "save-list-person");
        if(!folder.exists()){
            Toast.makeText(this, "khong co du lieu nao duoc luu", Toast.LENGTH_SHORT).show();

        }else{

            File file = new File(folder, "list-person.json");
            Gson gson = new Gson();

            FileInputStream fileInputStream;
            StringBuilder stringBuilder = new StringBuilder();
            try {
                fileInputStream = new FileInputStream(file);

                if(fileInputStream != null){
                    InputStreamReader streamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(streamReader);
                    String line ="";
                    while((line = bufferedReader.readLine())!= null){
                        stringBuilder.append(line);
                    }
                    fileInputStream.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String json = stringBuilder.toString();
            if(json!=null){
                Type type =new TypeToken<ArrayList<Person>>(){}.getType();
                list = gson.fromJson(json, type);
                CustomListView customListView = new CustomListView(MainActivity.this, list);
                listView.setAdapter(customListView);
            }
        }

//            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
//            Bitmap bitmap = drawable.getBitmap();

    }

    public void btnThemClick(View view) {
        String ten = etTen.getText().toString();
        int tuoi = Integer.parseInt(etTuoi.getText().toString().trim());
        long id = list.size() + 1;
        String image = uri.toString();
        Person person = new Person(ten, tuoi, image);
        person.setId(id);
        list.add(person);

        CustomListView customListView = new CustomListView(MainActivity.this, list);
        listView.setAdapter(customListView);
    }
}