package com.example.fragmentdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PushData{
//    GridView gridView;
    private static Product product = null;
    FragmentManager fragmentManager;
    FragmentInfo  info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        Configuration configuration = getResources().getConfiguration();
        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            FragmentList fragmentList = (FragmentList) getFragmentManager().findFragmentById(R.id.fragmentContainerView4);
            fragmentList.setNumColumns(4);
        }else if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){

            if(product != null){
                info  = (FragmentInfo) getFragmentManager().findFragmentById(R.id.fragmentContainerView3);
                if(info != null && info.isInLayout()){
                    info.setData(product);
                }
            }
        }
    }



    @Override
    public void DataProduct(Product product) {
        this.product = product;
        info = (FragmentInfo) getFragmentManager().findFragmentById(R.id.fragmentContainerView3);
        if(info != null && info.isInLayout()){
            info.setData(product);
        }else{
            Bundle bundle = new Bundle();
            bundle.putSerializable("p", product);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            FragmentInfo fragmentInfo = new FragmentInfo();
            fragmentInfo.setArguments(bundle);
            transaction.add(R.id.frLayout,fragmentInfo , "frInfor");
            transaction.addToBackStack("frInfor");
            transaction.commit();
        }

    }

    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStack();
        }else
            super.onBackPressed();
    }
}