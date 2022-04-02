package com.example.fragmentdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class FragmentInfo extends Fragment {
    ImageView imgCTSP;
    TextView tvDetail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        imgCTSP = view.findViewById(R.id.imgCTSP);
        tvDetail = view.findViewById(R.id.tvDetail);


        return view;
    }

    public void setData(Product product) {
        imgCTSP.setImageResource(product.getImage());
        tvDetail.setText(product.getDetail());
    }
}
