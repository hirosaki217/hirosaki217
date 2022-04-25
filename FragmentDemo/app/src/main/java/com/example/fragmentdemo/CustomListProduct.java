package com.example.fragmentdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class CustomListProduct extends BaseAdapter {
    private List<Product> list;
    private Context context;
    private int layout;

    public CustomListProduct(Context context, int layout, List<Product> list) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
        }
        ImageView imageView = view.findViewById(R.id.imgSP);
        TextView tvCTSP = view.findViewById(R.id.tvCTSP);
        TextView tvGiaSP = view.findViewById(R.id.tvGiaSP);

        imageView.setImageResource(list.get(i).getImage());
        tvCTSP.setText(list.get(i).getName());
        tvGiaSP.setText(list.get(i).getPrice());
        return view;
    }
}
