package tuan2_1.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public float convertToCelcius(float F){
        return (F-32)* 5/9f;
    }
    public float convertToFahrenheit(float C){
        return C* 9/5f + 32;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText f_et = findViewById(R.id.f_et);
        EditText c_et = findViewById(R.id.c_et);

        Button ctc_btn = findViewById(R.id.ctc_btn);
        Button ctf_btn = findViewById(R.id.ctf_btn);
        Button clear_btn = findViewById(R.id.clear_btn);


        ctc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float f = 0;
                try {
                    f = Float.parseFloat(f_et.getText().toString());

                }catch (Exception e){
                    Toast.makeText(view.getContext(), "vui lòng nhập nhiệt độ cần đổi", Toast.LENGTH_SHORT).show();
                    return;
                }
                c_et.setText(String.valueOf(convertToCelcius(f)));
            }
        });
        ctf_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                float c = 0;
                try {
                    c = Float.parseFloat(c_et.getText().toString());

                }catch (Exception e){
                    Toast.makeText(view.getContext(), "vui lòng nhập nhiệt độ cần đổi", Toast.LENGTH_SHORT).show();
                    return;
                }

                f_et.setText(String.valueOf(convertToFahrenheit(c)));
            }
        });
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_et.setText("");
                c_et.setText("");
            }
        });

    }
}