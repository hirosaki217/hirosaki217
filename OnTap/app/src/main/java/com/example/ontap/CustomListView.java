package com.example.ontap;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomListView extends ArrayAdapter {
    private Activity activity;
    private List<Person> resource;
    public CustomListView(@NonNull Activity context, List<Person> resource) {
        super(context,R.layout.row_item ,resource);
        this.activity = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutInflater inflater = activity.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, false);
        TextView textViewName = (TextView) row.findViewById(R.id.textViewName);
        TextView tvTuoi = (TextView) row.findViewById(R.id.tvTuoi);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewAvatar);

        textViewName.setText("Tên: "+resource.get(position).getName());
        tvTuoi.setText("Tuổi: " + resource.get(position).getAge());
        imageFlag.setImageURI(Uri.parse(resource.get(position).getImage()));
        return row;
    }
}
