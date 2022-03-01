package com.example.imageview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePickerDialog durationPicker;
    CountDownTimer countDownTimer;
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
    private int lastMinute = -1;
    private int lastSecond = -1;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3 ;
    ImageView iv4 ;
    ImageView iv5 ;
    ImageView iv6 ;
    TextView thongBao ;
    TextView tiSoBot ;
    TextView tiSoPlayer ;
    TextView luotChoi ;
    Button btn ;
    Button playBtn ;
    TextView result;
    ConstraintLayout view_game;
    TextView duration;
    boolean isStart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);
        thongBao = findViewById(R.id.thongbao);
        tiSoBot = findViewById(R.id.ti_so_bot);
        tiSoPlayer = findViewById(R.id.ti_so_player);
        luotChoi = findViewById(R.id.luot_choi);
        btn = findViewById(R.id.pull);
        playBtn = findViewById(R.id.play);
        result = findViewById(R.id.ketqua);
        view_game = findViewById(R.id.view_game);
        duration = findViewById(R.id.duration);


        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDurationPicker();
                isStart = false;
                btn.setText("Bắt đầu");
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

                isStart = !isStart;
                if(isStart){
                    startCountDown();
                    btn.setText("Dừng");
                }else{
                    btn.setVisibility(btn.INVISIBLE);
                    playBtn.setVisibility(playBtn.VISIBLE);
                    luot = 0;
                    diemBot = 0;
                    diemPlayer =0;
                    String time = "00:00";
                    duration.setText(time);
                    countDownTimer.cancel();
                    btn.setText("Bắt đầu");
                }
            }
        });

    }
    private void startCountDown(){
        int second = lastMinute*60 + lastSecond;
        countDownTimer = new CountDownTimer( second*1000+100,1000) {
            @Override
            public void onTick(long l) {
                int second =(int) l/1000;
                int minute = second/60;
                int s = second - (minute * 60);
                duration.setText(getTimeString(minute, s));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                                        startPlay();

                    }
                });
            }

            @Override
            public void onFinish() {
                if(diemBot > diemPlayer){
                    thongBao.setText("Đỏ thắng");
                }else if(diemBot < diemPlayer){
                    thongBao.setText("Xanh Thắng");
                }else{
                    thongBao.setText("Hòa");
                }
                btn.setVisibility(btn.INVISIBLE);
                playBtn.setVisibility(playBtn.VISIBLE);
                luot = 0;
                diemBot = 0;
                diemPlayer =0;
                String time = "00:00";
                duration.setText(time);
            }
        }.start();
    }

    private void startPlay(){
        luotChoi.setText(luot+"");

                int[] temp = layNgauNhien(0, 51, 6);
                baso1 = setArray(0, 3, temp);
                baso2 = setArray(3, 6, temp);

                iv1.setImageResource(manghinhbai[baso1[0]]);
                iv2.setImageResource(manghinhbai[baso1[1]]);
                iv3.setImageResource(manghinhbai[baso1[2]]);

                iv4.setImageResource(manghinhbai[baso2[0]]);
                iv5.setImageResource(manghinhbai[baso2[1]]);
                iv6.setImageResource(manghinhbai[baso2[2]]);


                int ketQua2Ben = soSanh(baso1, baso2);
                if(ketQua2Ben == 1){
                    diemBot++;
                    tiSoBot.setText(String.valueOf(diemBot));
                }else if(ketQua2Ben ==  -1){
                    diemPlayer++;
                    tiSoPlayer.setText(String.valueOf(diemPlayer));
                }

                luot++;


    }
    private String getTimeString(int minute, int second){
        String s = String.format("%2d", second);
        String m = String.format("%2d", minute);
        if(second <=9)
            s = "0"+second;
        if(minute <= 9)
            m = "0"+minute;

        return m + ":" +s;
    }
    private void getDurationPicker(){
        if(lastMinute == -1){
            final Calendar c = Calendar.getInstance();
            lastMinute = c.get(Calendar.MINUTE);
            lastSecond = c.get(Calendar.SECOND);
        }
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int minute, int second) {
                lastMinute = minute;
                lastSecond = second;
                duration.setText(getTimeString(minute, second));
            }
        };
        durationPicker = new TimePickerDialog(MainActivity.this, timeSetListener, lastMinute, lastSecond, true);
        durationPicker.show();

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