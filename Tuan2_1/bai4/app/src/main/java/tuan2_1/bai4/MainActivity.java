package tuan2_1.bai4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText fullName;
    EditText id;
    RadioGroup radioGroup;
    CheckBox readPaper, readBook, readCode;
    EditText info;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = findViewById(R.id.send_btn);
        fullName = findViewById(R.id.name_et);
        id = findViewById(R.id.id_et);
        radioGroup =findViewById(R.id.radio_rg);
        readPaper = findViewById(R.id.box_1);
        readBook = findViewById(R.id.box_2);
        readCode = findViewById(R.id.box_3);
        info = findViewById(R.id.info_et);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String level = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId()))
                        .getText().toString();
                String name = fullName.getText().toString();
                int identify = -1;
                String temp = id.getText().toString().trim();
                try {


                    identify = Integer.parseInt(temp);

                }catch (Exception e){
                    Toast.makeText(view.getContext(), "cmnd chỉ nhận ký tự số", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(temp.length()!= 9){
                    Toast.makeText(view.getContext(), "cmnd phải có đủ 9 số", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<String> favorites = new ArrayList<String>();
                if(readPaper.isChecked())
                    favorites.add("Đọc báo");
                if(readBook.isChecked())
                    favorites.add("Đọc sách");
                if(readCode.isChecked())
                    favorites.add("Đọc coding");
                String favoriteAll = "";
                for (int i =0; i< favorites.size(); i++){
                    favoriteAll += favorites.get(i)+ ", ";
                }

                String inf= info.getText().toString();
                Toast.makeText(view.getContext(), level, Toast.LENGTH_SHORT).show();
                Toast.makeText(view.getContext(), favoriteAll, Toast.LENGTH_SHORT).show();
            }
        });
    }
}