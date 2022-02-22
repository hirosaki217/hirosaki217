package tuan2_1.bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final CharSequence[] can = {"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Đinh", "Mậu", "Kỷ"};
    public  final CharSequence[] chi = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mẹo", "Thìn","Tỵ", "Ngọ", "Mùi"};
    public String convertAL(int year){
        int index_can = year % 10;
        int index_chi = year % 12;

        String result =can[index_can] + " " +chi[index_chi];
        return result;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText year_et = findViewById(R.id.year_et);
        Button convert = findViewById(R.id.convert_btn);
        TextView year_convert =  findViewById(R.id.year_convert_tv);


        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = 0;
                try {
                    year = Integer.parseInt(year_et.getText().toString());
                }catch (Exception e){
                    Toast.makeText(view.getContext(), "dữ liệu nhập vào phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(year < 1900){
                    Toast.makeText(view.getContext(), "năm chỉ được nhập >= 1900", Toast.LENGTH_SHORT).show();
                    return;
                }
                year_convert.setText(convertAL(year));
            }
        });
    }
}