package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView imageView = findViewById(R.id.imDetail);
        TextView tvDetail = findViewById(R.id.tvTitileDetail);
        TextView tvPriceDetail = findViewById(R.id.tvPriceDetail);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);
        DoGiaDung doGiaDung = DataDefault.getList().get(index);
        imageView.setImageResource(doGiaDung.getImage());
        tvDetail.setText(doGiaDung.getTitle());
        tvPriceDetail.setText(String.valueOf(doGiaDung.getPrice()));
    }

    public void btnTroLai(View view) {
        finish();
    }
}