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
    TextView tvTenCTSP;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        imgCTSP = view.findViewById(R.id.imgCTSP);
        tvDetail = view.findViewById(R.id.tvDetail);
        tvTenCTSP = view.findViewById(R.id.tvTenCTSP);


        Bundle bundle = getArguments();
        if (bundle!= null){
            Product product = (Product) bundle.getSerializable("p");
            setData(product);
        }
        return view;
    }

    public void setData(Product product) {
        imgCTSP.setImageResource(product.getImage());
        tvDetail.setText(product.getDetail());
        tvTenCTSP.setText(product.getName());
    }
}
