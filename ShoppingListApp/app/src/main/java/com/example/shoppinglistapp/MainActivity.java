package com.example.shoppinglistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void catalog(View view) {
        Intent getCatalog = new Intent(this, Catalog.class);
        startActivity(getCatalog);
    }

    public void edit_catalog(View view) {
        Intent editCatalog = new Intent(this, Edit_Catalog.class);
        startActivity(editCatalog);
    }

    public void create_item(View view) {
        Intent createItemIntent = new Intent(this, Create_Item.class);
        startActivity(createItemIntent);
    }

    @Override
    public void onBackPressed() {
    }
}