package com.example.shoppinglistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Catalog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        ListView catalog_listView = findViewById(R.id.catalog_listView);

        ItemListService itemList = new ItemListService(Catalog.this);
        itemList.getItemList(new ItemListService.ItemListResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(Catalog.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<ItemListModel> response) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(Catalog.this, android.R.layout.simple_list_item_1, response);
                catalog_listView.setAdapter(arrayAdapter);
            }
        });
    }
}