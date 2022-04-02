package com.example.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ActivityInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("product");
        FragmentInfo  info = (FragmentInfo) getFragmentManager().findFragmentById(R.id.fragmentContainerView5);
        info.setData(p);

    }
}