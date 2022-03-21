package com.example.ontapgk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText etMaNv;
    EditText etTenNV;
    EditText etNgaySinh;
    EditText etDiaChi;
    Button btnNgaySinh;
    Button btnNhapMoi;
    Button btnLuu;
    Button btnChuyen;
    NhanVien nv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMaNv = findViewById(R.id.etMaNv);
        etTenNV = findViewById(R.id.etTenNV);
        etNgaySinh = findViewById(R.id.etNgaySinh);
        etNgaySinh.setEnabled(false);
        etDiaChi = findViewById(R.id.etDiaChi);
        btnNgaySinh = findViewById(R.id.btnNgaySinh);
        btnNhapMoi = findViewById(R.id.btnNhapMoi);
        btnLuu = findViewById(R.id.btnLuu);
        btnChuyen = findViewById(R.id.btnChuyen);
        Locale locale = new Locale("vi", "VN");
        Locale.setDefault(locale);

        btnNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        etNgaySinh.setText(day+ "/" + month+"/"+ year);

                    }
                };


                DatePickerDialog c = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar, dateSetListener,2000, 1, 1);

                c.show();
            }
        });

        btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etMaNv.setText("");
                etTenNV.setText("");
                etDiaChi.setText("");
                etNgaySinh.setText("");
                etMaNv.requestFocus();

            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNV = etMaNv.getText().toString().trim();
                String tenNV = etTenNV.getText().toString().trim();
                String diaChi = etDiaChi.getText().toString().trim();
                String ngaySinh = etNgaySinh.getText().toString().trim();

                nv = new NhanVien(maNV, tenNV, diaChi, ngaySinh);

                File path = new File(getApplicationContext().getFilesDir(), "danhsachnhanvien");
                if(!path.exists())
                    path.mkdir();

                Gson gson = new Gson();
                String data = gson.toJson(nv);
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File(path, nv.getMaNV()+".txt"));
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    Toast.makeText(MainActivity.this, "Đã lưu nhân viên "+ nv.getMaNV(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        btnChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivitiB.class);
                startActivity(intent);
            }
        });

    }
}