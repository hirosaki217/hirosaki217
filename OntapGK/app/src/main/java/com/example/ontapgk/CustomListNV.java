package com.example.ontapgk;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.zip.Inflater;

public class CustomListNV extends ArrayAdapter {
    File nv[];
    Activity context;
    int select = 0;
    TextView tv;
    public CustomListNV(@NonNull Activity context, File[] nv) {
        super(context, R.layout.list_nv, nv);
        this.context = context;
        this.nv = nv;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView == null)
            row = inflater.inflate(R.layout.list_nv, null, false);

        RadioButton radioButton = row.findViewById(R.id.rdNv);
        tv = row.findViewById(R.id.tvChiTiet);
        radioButton.setChecked(select == position);
        radioButton.setTag(position);
        radioButton.setText(nv[position].getName().split("\\.")[0]);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select = (Integer) radioButton.getTag();



                docFile(nv[position].getPath());

                notifyDataSetChanged();

            }
        });
        return row;



    }
    public void docFile(String fileName){
        StringBuffer stringBuffer = new StringBuffer();
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = new FileInputStream(new File(fileName));
            if(fileInputStream!= null){
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


                String line = "";
                while((line = bufferedReader.readLine())!= null){
                    stringBuffer.append(line);
                }
                fileInputStream.close();
                String data = stringBuffer.toString();

               if(data != null){
//                   Type type= new TypeToken<NhanVien>(){}.getType();
                   NhanVien nv = new Gson().fromJson(data, NhanVien.class);

                   System.out.println(nv.toString());
                   tv = context.findViewById(R.id.tvChiTiet);
                   tv.setText(nv.toString());
               }
            }





        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
