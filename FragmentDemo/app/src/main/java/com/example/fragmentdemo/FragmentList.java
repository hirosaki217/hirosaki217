package com.example.fragmentdemo;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment {
    List<Product> products;
    PushData pushData;
    GridView gridView;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        products = new ArrayList<>();
        addData();
        view = inflater.inflate(R.layout.fragment_list, container, false);

        CustomListProduct adapter = new CustomListProduct(view.getContext() ,R.layout.row_item, products);
        gridView =(GridView) view.findViewById(R.id.listItem);

        gridView.setAdapter(adapter);

        pushData = (PushData) getActivity();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pushData.DataProduct(products.get(i));
            }
        });
        return view;
    }
    public void setNumColumns(int n){

        gridView.setNumColumns(n);
    }

    public void addData(){
        products.add(new Product(1, "Sữa hello", getResources().getString(R.string.sua_hello), "56.900 đ", R.drawable.sua_hello));
        products.add(new Product(2, "Sữa chua lên men tự nhiên Dutch Lady Organic", getResources().getString(R.string.sua_chua_len_men_tu_nhien_Dutch_Lady_Organic), "75.600 đ", R.drawable.sua_chua_len_men_tu_nhien_dutch_lady_organic));
//        products.add(new Product(3, "Sữa dinh dưỡng có đường", getResources().getString(R.string.sua_dinh_duong_co_duong), "14.200 d", R.drawable.sua_dinh_duong_co_duong));
//        products.add(new Product(4, "Sữa Nutriboost hương dâu", getResources().getString(R.string.Nutriboost_huong_dau), "10.500 đ", R.drawable.nutriboost_huong_dau));
//        products.add(new Product(5, "Creamer sữa đặc có đường hương dâu", getResources().getString(R.string.Creamer_sua_dac_co_duong_huong_dau), "12.000 đ", R.drawable.creamer_sua_dac_co_duong_huong_dau));
//        products.add(new Product(6, "Sữa chua yomost vị cam", getResources().getString(R.string.sua_chua_yomost_vi_cam), "20.000 đ", R.drawable.sua_chua_yomost_vi_cam));
//        products.add(new Product(7, "Sữa đặc ngôi sao phương nam", getResources().getString(R.string.sua_dac_ngoi_sao_phuong_nam), "18.200 đ", R.drawable.sua_dac_ngoi_sao_phuong_nam));
//        products.add(new Product(8, "Sữa tươi tiệt trùng Dutch Lady Organic", getResources().getString(R.string.sua_tuoi_tiet_trung_Dutch_Lady_Organic), "11.000đ", R.drawable.sua_tuoi_tiet_trung_dutch_lady_organic));
//        products.add(new Product(9, "Sữa yến mạch đậu đỏ", getResources().getString(R.string.sua_yen_mach_dau_do), "12345", R.drawable.sua_yen_mach_dau_do));
//        products.add(new Product(10, "Sữa chua lên men hương việt quất", getResources().getString(R.string.sua_chua_lenmen_tu_nhien_viet_quat), "12345", R.drawable.sua_chua_lenmen_tu_nhien_viet_quat));

    }
}
