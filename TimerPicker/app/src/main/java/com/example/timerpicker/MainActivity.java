package com.example.timerpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView timeText = null;
    private Button btnPicker = null;
    private int lastSelectHour = -1;
    private int lastSelectMinute = -1;
    private CountDownTimer countDownTimer;
    private Button btnCountDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText = findViewById(R.id.timeText);
        btnPicker = findViewById(R.id.btnPicker);
        btnCountDown= findViewById(R.id.btnCountDown);
        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSelectTime();
            }
        });
        btnCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long second = lastSelectHour*3600+lastSelectMinute*60;
                Toast.makeText(getApplicationContext(), second+"s" , Toast.LENGTH_SHORT).show();
                countDownTimer = new CountDownTimer(second*1000, 1000) {
                    @Override
                    public void onTick(long l) {
                        updateTimer((int) l/1000);
                    }

                    @Override
                    public void onFinish() {
                    }
                }.start();
            }
        });
    }
    private void updateTimer(int secondLeft){
        int hours = secondLeft/3600;
        int minutes = 0;
        if(hours > 0)
            minutes =(secondLeft - hours*3600 )/60;
        else{
            minutes = secondLeft/60;
        }
        int seconds = 0;
        if(minutes > 0)
            seconds =secondLeft - minutes*60;
        else{
            seconds = secondLeft;
        }

        timeText.setText(hours +":" + minutes+":"+ seconds);
    }
    private void buttonSelectTime() {

        if(lastSelectHour == -1){
            final Calendar c = Calendar.getInstance();
            lastSelectHour = c.get(Calendar.HOUR_OF_DAY);
            lastSelectMinute = c.get(Calendar.MINUTE);
        }
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int minute) {
                timeText.setText(hours +":" +minute+":"+"00");
                lastSelectHour = hours;
                lastSelectMinute = minute;
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timeSetListener, lastSelectHour, lastSelectMinute, true);

        timePickerDialog.show();
    }
}