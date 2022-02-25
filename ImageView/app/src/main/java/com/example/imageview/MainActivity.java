package com.example.imageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int luot = 1;
    int diemBot = 0;
    int diemPlayer = 0;
    int manghinhbai[]={
            R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
            R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,
            R.drawable.cj,R.drawable.cq,R.drawable.ck,
            R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,
            R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,
            R.drawable.dj,R.drawable.dq,R.drawable.dk,
            R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,
            R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
            R.drawable.hj,R.drawable.hq,R.drawable.hk,
            R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,
            R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,
            R.drawable.sj,R.drawable.sq,R.drawable.sk};
    int[] baso1;
    int[] baso2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv1 = findViewById(R.id.imageView1);
        ImageView iv2 = findViewById(R.id.imageView2);
        ImageView iv3 = findViewById(R.id.imageView3);
        ImageView iv4 = findViewById(R.id.imageView4);
        ImageView iv5 = findViewById(R.id.imageView5);
        ImageView iv6 = findViewById(R.id.imageView6);
        TextView thongBao = findViewById(R.id.thongbao);
        TextView tiSoBot = findViewById(R.id.ti_so_bot);
        TextView tiSoPlayer = findViewById(R.id.ti_so_player);
        TextView luotChoi = findViewById(R.id.luot_choi);
        Button btn = findViewById(R.id.pull);
        Button playBtn = findViewById(R.id.play);
        TextView result = findViewById(R.id.ketqua);
        ConstraintLayout view_game = findViewById(R.id.view_game);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setVisibility(Button.VISIBLE);
                view_game.setVisibility(view_game.VISIBLE);
                playBtn.setVisibility(playBtn.INVISIBLE);
                tiSoBot.setText(String.valueOf(diemBot));
                tiSoPlayer.setText(String.valueOf(diemPlayer));
                thongBao.setText("");
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luotChoi.setText(luot+"/10");
                if(luot == 10 ){
                    if(diemBot > diemPlayer){
                        thongBao.setText("Máy Thắng");
                    }else if(diemBot < diemPlayer){
                        thongBao.setText("Bạn Thắng");
                    }else{
                        thongBao.setText("Hòa");
                    }
                    btn.setVisibility(btn.INVISIBLE);
                    playBtn.setVisibility(playBtn.VISIBLE);
                    luot = 0;
                    diemBot = 0;
                    diemPlayer =0;

                    return;
                }
                int[] temp = layNgauNhien(0, 51, 6);
                baso1 = setArray(0, 3, temp);
                baso2 = setArray(3, 6, temp);

                iv1.setImageResource(manghinhbai[baso1[0]]);
                iv2.setImageResource(manghinhbai[baso1[1]]);
                iv3.setImageResource(manghinhbai[baso1[2]]);

                iv4.setImageResource(manghinhbai[baso2[0]]);
                iv5.setImageResource(manghinhbai[baso2[1]]);
                iv6.setImageResource(manghinhbai[baso2[2]]);

//                int soTay = soTay(baso1);
//
//                if(soTay == 3) {
//                    result.setText("3 tây");
//                }else {
//                    int soNut = soNut(baso1);
//                    if(soTay != 0)
//                        result.setText("số nút là "+ soNut%10 + "số tây: " + soTay);
//                    else{
//                        result.setText("số nút là "+ soNut%10);
//                    }
//                }
                int ketQua2Ben = soSanh(baso1, baso2);
                if(ketQua2Ben == 1){
                    diemBot++;
                    tiSoBot.setText(String.valueOf(diemBot));
                }else if(ketQua2Ben ==  -1){
                    diemPlayer++;
                    tiSoPlayer.setText(String.valueOf(diemPlayer));
                }

                luot++;

//                if(luot > 10){
//                    luot = 0;
//                    view_game.setVisibility(view_game.INVISIBLE);
//                    btn.setVisibility(btn.INVISIBLE);
//                    playBtn.setVisibility(playBtn.VISIBLE);
//                }
            }
        });

    }
    private int[] setArray(int b, int a, int[] arr){
        int temp[] = new int[3];
        int j =0;
        for(int i= b; i< a; i++){
            temp[j++] = arr[i];
        }
        return temp;
    }

    private int[] layNgauNhien(int min, int max, int n){
        int[] baso = new int[n];
        int i = 0;
        baso[i++] = random(min, max);
        do {
            int k = random(min, max);
            if(kiemTraTrung(k, baso))
                baso[i++] = k;
        }while (i< n);
        return baso;
    }
    private boolean kiemTraTrung(int k, int[] arr){
        for(int i=0; i < arr.length; ++i){
            if(arr[i] == k)
                return false;
        }
        return true;
    }
    private int random(int min, int max){
        return min + (int)(Math.random()* ((max-min)+1));
    }

    private int count(int i){
        if(i>8)
            return 10;
        return i+1;
    }

    private int soNut(int[] arr){
        int nut = 0;
        for(int i=0; i< arr.length; i++){
            nut += count(arr[i]%13);
        }
        return nut;
    }
    private int soTay(int[] arr){
        int tay = 0;
        for(int i=0; i< arr.length; i++){
            if(arr[i]%13 >= 10 && arr[i]%13 <13)
            tay += 1;
        }
        return tay;
    }
    private int ketQua(int[] arr){
        int soTay = soTay(arr);
        if(soTay == 3) {
            return 31;
        }
        int soNut = soNut(arr);
        if(soNut == 30)
            return -1;
        return soNut%10;
    }

    private int soSanh(int[] arr1, int[] arr2){
        int k1 = ketQua(arr1);
        int k2 = ketQua(arr2);
        if(k1 == k2)
            return 0;
        if(k1 > k2)
            return 1;
        return -1;
    }
}