package com.example.gridview;

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

public class CustomList extends ArrayAdapter {
    List<DoGiaDung> doGiaDungList;
    Activity context;
    public CustomList(@NonNull Activity context,  List<DoGiaDung> doGiaDungList) {
        super(context,  R.layout.custom,doGiaDungList);
        this.context = context;
        this.doGiaDungList = doGiaDungList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row= convertView;
        LayoutInflater inflater  = context.getLayoutInflater();
        if (convertView == null)
            row = inflater.inflate(R.layout.custom, null, false);
        ImageView imageView = row.findViewById(R.id.image);
        TextView tv = row.findViewById(R.id.tvInfo);
        TextView tvPrice = row.findViewById(R.id.tvPrice);
        System.out.println(R.drawable.quat);
        imageView.setImageResource(doGiaDungList.get(position).getImage());
        tv.setText(doGiaDungList.get(position).getName());
        tvPrice.setText(String.valueOf(doGiaDungList.get(position).getPrice()));
        return row;
    }
}
