package com.example.shoppinglistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Create_Item extends AppCompatActivity {
    String url ="http://192.168.1.47:8080/AndroidWebService/rest/webservice/android/createitem";

    EditText createItem_ID;
    EditText createItem_Name;
    EditText createItem_Cost;
    EditText createItem_Category;
    Button createItem_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        createItem_ID = findViewById(R.id.ItemCreate_ID);
        createItem_Name = findViewById(R.id.ItemCreate_Name);
        createItem_Cost = findViewById(R.id.ItemCreate_Cost);
        createItem_Category = findViewById(R.id.ItemCreate_Category);
        createItem_save = findViewById(R.id.ItemCreate_CreateItem);

        createItem_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest putRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Create_Item.this, "The Request was: // " + response + " //", Toast.LENGTH_LONG).show();
                        Intent newIntent = new Intent(Create_Item.this, MainActivity.class);
                        startActivity(newIntent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Create_Item.this, "Error with Response! : "+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("\"newItemID\"", "\""+createItem_ID.getText().toString()+"\"");
                        params.put("\"itemName\"", "\""+createItem_Name.getText().toString()+"\"");
                        params.put("\"itemCost\"", "\""+createItem_Cost.getText().toString()+"\"");
                        params.put("\"itemCategory\"", "\""+createItem_Category.getText().toString()+"\"");

                        return params;
                    }
                };
                MySingleton.getInstance(Create_Item.this).addToRequestQueue(putRequest);
            }
        });
    }
}