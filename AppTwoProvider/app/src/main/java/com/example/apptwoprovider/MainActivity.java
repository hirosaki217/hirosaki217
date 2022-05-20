package com.example.apptwoprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etId, etName, etUnit, etMadeIn;
    private GridView productList;
    static final String AUTHORITY = "com.example.apponeprovider";
    static final String CONTENT_PROVIDER = "contentprovider";
    static final String URL ="content://"+AUTHORITY+"/"+ CONTENT_PROVIDER;
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final String PRODUCT_TABLE = "Products";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etUnit = findViewById(R.id.etUnit);
        etMadeIn = findViewById(R.id.etMadeIn);
        productList = findViewById(R.id.productList);
    }

    public void btnExit(View view) {
    }

    public void btnSelect(View view) {
        List<String> strings = new ArrayList<>();
        String id = etId.getText().toString().trim();
        String select = id.length() > 0 ? " id =?" : null;
        String[] selectionArgs = id.length() > 0 ? new String[]{ id}: null;
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, select, selectionArgs, "name");
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {

                strings.add(cursor.getInt(0)+"");

                strings.add(cursor.getString(1));

                strings.add(cursor.getString(2));
                strings.add(cursor.getString(3));
            }
            while (cursor.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, strings);
            productList.setAdapter(adapter);
        }
    }

    public void btnSave(View view) {
        ContentValues values = new ContentValues();
        values.put("id", etId.getText().toString());
        values.put("name", etName.getText().toString());
        values.put("unit", etUnit.getText().toString());
        values.put("madein", etMadeIn.getText().toString());
        Uri insert = getContentResolver().insert(CONTENT_URI, values);
    }

    public void btnDelete(View view) {
    }

    public void btnUpdate(View view) {
    }
}