package com.example.fragmentdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity implements PushData{
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.listItem);
        Configuration configuration = getResources().getConfiguration();
        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            gridView.setNumColumns(4);
        }

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//
//        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            gridView.setNumColumns(4);
//        }else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
//            gridView.setNumColumns(1);
//        }
    }

    @Override
    public void DataProduct(Product product) {
        FragmentInfo  info = (FragmentInfo) getFragmentManager().findFragmentById(R.id.fragmentContainerView3);
        if(info != null && info.isInLayout()){
            info.setData(product);
        }else{
            Intent intent = new Intent(MainActivity.this, ActivityInfo.class);
            intent.putExtra("product", product);
            startActivity(intent);
        }

    }
}