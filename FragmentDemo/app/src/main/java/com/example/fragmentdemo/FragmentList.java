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
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        products = new ArrayList<>();
        addData();
        view = inflater.inflate(R.layout.fragment_list, container, false);

        CustomListProduct adapter = new CustomListProduct(view.getContext() ,R.layout.row_item, products);
        GridView gridView =(GridView) view.findViewById(R.id.listItem);

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
    public GridView getListView(){
        GridView gridView =(GridView) view.findViewById(R.id.listItem);
        return gridView;
    }

    public void addData(){
        products.add(new Product(1, "sua", "sua du", "12345", R.drawable.img));
        products.add(new Product(2, "sua", "sua du", "12345", R.drawable.sua_hello));
        products.add(new Product(3, "sua", "sua du", "12345", R.drawable.img));
        products.add(new Product(4, "sua", "sua du", "12345", R.drawable.img));
        products.add(new Product(5, "sua", "sua du", "12345", R.drawable.img));
        products.add(new Product(6, "sua", "sua du", "12345", R.drawable.img));
        products.add(new Product(7, "sua", "sua du", "12345", R.drawable.img));


    }
}
