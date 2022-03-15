package com.example.uidemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText hoTen;
    private EditText maSo;
    private RadioGroup rg;
    private RadioButton namRb;
    private RadioButton nuRb;
    private Button themBtn;
    private Button suaBtn;
    private Button truyVanBtn;
    private ListView listView;
    private List<Employee> employeeList;
    private String[] listItems;
    private Spinner spinnerDonVi;
    private String donVi = null;
    private ImageView imageViewAnh;
    private Button buttonChonAnh;
    private Uri uri = null;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employeeList  = new ArrayList<Employee>();
        spinnerDonVi = findViewById(R.id.donvi_spinner);
        hoTen = findViewById(R.id.hoten_et);
        maSo = findViewById(R.id.maso_et);
        rg = findViewById(R.id.rg);
        namRb = findViewById(R.id.nam_rd);
        nuRb = findViewById(R.id.nu_rd);
        themBtn  = findViewById(R.id.them_btn);
        suaBtn = findViewById(R.id.sua_btn);
        truyVanBtn = findViewById(R.id.truyvan_btn);
        listView = findViewById(R.id.list_item);
        imageViewAnh = findViewById(R.id.imageViewAnh);
        buttonChonAnh = findViewById(R.id.buttonChonAnh);


        listItems = getResources().getStringArray(R.array.donvi_list);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listItems);


        spinnerDonVi.setAdapter(adapter);
        spinnerDonVi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                donVi = listItems[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        themBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ma = Integer.parseInt(maSo.getText().toString());
                String hoten = hoTen.getText().toString();
                String gioitinh = ((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                Uri image = uri;
                Employee nv = new Employee(ma, hoten, gioitinh, donVi, image.toString());
                if(!employeeList.contains(nv))
                    employeeList.add(nv);
//                ArrayList<String> listNv = new ArrayList<String>();
//                for (Employee nv1 : employeeList){
//                    listNv.add(nv1.toString());
//                }
                InformationEmployeeList adapterListNV = new InformationEmployeeList(MainActivity.this, employeeList);
                listView.setAdapter(adapterListNV);
            }
        });
        suaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        truyVanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee nv = employeeList.get(i);
                maSo.setText(nv.getMaSo()+"");
                hoTen.setText(nv.getHoTen());
                if(nv.getGioiTinh().equals("Nam"))
                    namRb.setChecked(true);
                else
                    nuRb.setChecked(true);
                for (int j=0; j< listItems.length; j++){
                    if(nv.getDonVi().equals(listItems[j])){
                        spinnerDonVi.setSelection(j);
                    }
                }

                imageViewAnh.setImageURI(Uri.parse(nv.getImageId()));

            }
        });

        buttonChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kiem tra quyen

                    if(checkSelfPermission((Manifest.permission.READ_EXTERNAL_STORAGE))
                            == PackageManager.PERMISSION_DENIED){
                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    }else{
                        pickImageFromGallery();
                    }

            }
        });
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }else{
                    Toast.makeText(this, "cấp quyền thất bại, vui lòng cấp quyền để truy cập thư viện", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){

            uri = data.getData();

//            Toast.makeText(MainActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();
            imageViewAnh.setImageURI(uri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mDocDuLieu:{


                SharedPreferences sharedPreferences = getSharedPreferences("shared employee", MODE_PRIVATE);
                Gson gson = new Gson();
                String json = sharedPreferences.getString("listEmployees", null);
                if(json!= null){
                    Type type = new TypeToken<ArrayList<Employee>>(){}.getType();
                    employeeList = gson.fromJson(json, type);
                }
                if(employeeList== null )
                    employeeList = new ArrayList<>();
                System.out.println(employeeList.size());
                InformationEmployeeList adapterListNV = new InformationEmployeeList(MainActivity.this, employeeList);
                listView.setAdapter(adapterListNV);
                break;
            }
            case R.id.mLuuDuLieu:{
//                if(checkStatus() == 1){
                    SharedPreferences sharedPreferences = getSharedPreferences("shared employee", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(employeeList);
                    editor.putString("listEmployees", json);
                    editor.apply();

//                }

                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

//    public int checkStatus(){
//        String state = Environment.getExternalStorageState();
//        if(state.equals(Environment.MEDIA_MOUNTED)){
//            return 1;
//        }
//        if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
//            return 0;
//        }
//        return -1;
//    }
}