package tuan2_1.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int gcd(int a, int b) {
        int tmp;
        while(b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText soa_tv = findViewById(R.id.tv_soa);
        EditText sob_tv = findViewById(R.id.tv_sob);
        TextView result_tv = findViewById(R.id.result_tv);

        Button plus_btn = findViewById(R.id.plus_btn);
        Button sub_btn = findViewById(R.id.sub_btn);
        Button mul_btn = findViewById(R.id.mul_btn);
        Button div_btn = findViewById(R.id.div_btn);
        Button gcd_btn = findViewById(R.id.gcd_btn);
        Button exit_btn = findViewById(R.id.exit_btn);

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int so_a = Integer.parseInt(soa_tv.getText().toString());
                int so_b = Integer.parseInt(sob_tv.getText().toString());
                result_tv.setText(String.valueOf(so_a+so_b));
            }
        });
        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int so_a = Integer.parseInt(soa_tv.getText().toString());
                int so_b = Integer.parseInt(sob_tv.getText().toString());
                result_tv.setText(String.valueOf(so_a-so_b));
            }
        });
        mul_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int so_a = Integer.parseInt(soa_tv.getText().toString());
                int so_b = Integer.parseInt(sob_tv.getText().toString());
                result_tv.setText(String.valueOf(so_a*so_b));
            }
        });
        div_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int so_a = Integer.parseInt(soa_tv.getText().toString());
                int so_b = Integer.parseInt(sob_tv.getText().toString());
                float result  = (float) (so_a/(so_b*1.0));
                result_tv.setText(String.valueOf(result));
            }
        });
        gcd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int so_a = Integer.parseInt(soa_tv.getText().toString());
                int so_b = Integer.parseInt(sob_tv.getText().toString());
                result_tv.setText(String.valueOf(gcd(so_a,so_b)));
            }
        });

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}