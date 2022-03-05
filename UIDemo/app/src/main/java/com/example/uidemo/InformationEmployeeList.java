package com.example.uidemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class InformationEmployeeList extends ArrayAdapter    {
    private Activity context;
    private List<Employee> employeeList;
    public InformationEmployeeList(Activity context,List<Employee> employeeList) {
        super(context, R.layout.row_item, employeeList);
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, false);
        TextView textViewName = (TextView) row.findViewById(R.id.textViewName);
        TextView textViewGender = (TextView) row.findViewById(R.id.textViewGender);
        TextView textViewUnit = (TextView) row.findViewById(R.id.textViewUnit);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewAvatar);

        textViewName.setText("Tên: "+employeeList.get(position).getHoTen());
        textViewGender.setText("Giới tính: "+employeeList.get(position).getGioiTinh());
        textViewUnit.setText("Đơn vị: "+employeeList.get(position).getDonVi());
        imageFlag.setImageURI(employeeList.get(position).getImageId());
        return row;
    }
}
