package com.example.shoppinglistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class Edit_Catalog extends AppCompatActivity {
    Button btn_submit;
    EditText entry_productID;
    ListView edit_lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_catalog);

        btn_submit = findViewById(R.id.edit_submit);
        entry_productID = findViewById(R.id.edit_productID);
        edit_lv = findViewById(R.id.edit_listView);

        EditCatalogService catalogService = new EditCatalogService(Edit_Catalog.this);

        ItemListService itemList = new ItemListService(Edit_Catalog.this);
        itemList.getItemList(new ItemListService.ItemListResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(Edit_Catalog.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<ItemListModel> response) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(Edit_Catalog.this, android.R.layout.simple_list_item_1, response);
                edit_lv.setAdapter(arrayAdapter);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                catalogService.getProductInfo(entry_productID.getText().toString(), new EditCatalogService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(Edit_Catalog.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response) throws JSONException {
                        Intent editItem = new Intent(Edit_Catalog.this, Edit_Item.class);

                        editItem.putExtra("itemID", response.getString("itemID"));
                        editItem.putExtra("itemName", response.getString("itemName"));
                        editItem.putExtra("itemCost", response.getString("itemCost"));
                        editItem.putExtra("itemCategory", response.getString("itemCategory"));

                        startActivity(editItem);
                    }
                });
            }
        });
    }
}