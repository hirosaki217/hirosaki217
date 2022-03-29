package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridDoGiaDung = findViewById(R.id.gridDoGiaDung);
        List<DoGiaDung> doGiaDungList = DataDefault.getList();



        CustomList arrayAdapter = new CustomList(this, doGiaDungList);
        gridDoGiaDung.setAdapter(arrayAdapter);


        gridDoGiaDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("index", i);
                startActivity(intent);
            }
        });

    }
}