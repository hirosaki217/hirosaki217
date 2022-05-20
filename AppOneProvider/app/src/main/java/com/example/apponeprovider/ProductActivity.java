package com.example.apponeprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private EditText etId, etName, etUnit, etMadeIn;
    private GridView productList;
    static final String uri = "content://com.example.apponeprovider";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etUnit = findViewById(R.id.etUnit);
        etMadeIn = findViewById(R.id.etMadeIn);
        productList = findViewById(R.id.productList);
    }

    public void btnSelect(View view) {
        List<String> strings = new ArrayList<>();
        Uri product = Uri.parse(uri);

        Cursor cursor = getContentResolver().query(product, null, null, null, "name");
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {

                strings.add(cursor.getInt(0)+"");

                strings.add(cursor.getString(1));

                strings.add(cursor.getString(2));
                strings.add(cursor.getString(3));
            }
            while (cursor.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter(ProductActivity.this, android.R.layout.simple_list_item_1, strings);
            productList.setAdapter(adapter);
        }
    }

    public void btnSave(View view) {
        ContentValues values = new ContentValues();
        values.put("id", etId.getText().toString());
        values.put("name", etName.getText().toString());
        values.put("unit", etUnit.getText().toString());
        values.put("madein", etMadeIn.getText().toString());
        Uri product = Uri.parse(uri);
        Uri insert = getContentResolver().insert(product, values);

    }

    public void btnDelete(View view) {
        String id = etId.getText().toString();
        Uri product = Uri.parse(uri);
        int rs = getContentResolver().delete(product, "id=?", new String[]{id});
        if(rs > 0)
            Toast.makeText(ProductActivity.this, "da xoa", Toast.LENGTH_SHORT).show();

    }

    public void btnUpdate(View view) {
        ContentValues values = new ContentValues();
        String id =etId.getText().toString();
        values.put("id", etId.getText().toString());
        values.put("name", etName.getText().toString());
        values.put("unit", etUnit.getText().toString());
        values.put("madein", etMadeIn.getText().toString());
        Uri product = Uri.parse(uri);
        int rs = getContentResolver().update(product, values, "id= ?", new String[]{id});
        if(rs > 0)
            Toast.makeText(ProductActivity.this, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(ProductActivity.this, "cap nhat that bai", Toast.LENGTH_SHORT).show();

        }
    }

    public void btnExit(View view) {
    }
}