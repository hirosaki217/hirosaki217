package com.example.ontapgk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.File;

public class ActivitiB extends AppCompatActivity {
    ListView lvNhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiti_b);

        lvNhanVien = findViewById(R.id.lvNhanVien);
        File file = new File(getApplicationContext().getFilesDir(), "danhsachnhanvien");
        File[] files =  file.listFiles();
        CustomListNV customListNV = new CustomListNV(this, files);

        lvNhanVien.setAdapter(customListNV);
    }

    public void btnDong(View view) {
        finish();
    }
}