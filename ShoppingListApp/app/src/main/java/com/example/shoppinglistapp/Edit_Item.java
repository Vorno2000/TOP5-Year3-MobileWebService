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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Edit_Item extends AppCompatActivity {
    Intent editItemIntent;
    String url ="http://192.168.1.47:8080/AndroidWebService/rest/webservice/android/edititem";

    EditText editItem_ID;
    EditText editItem_Name;
    EditText editItem_Cost;
    EditText editItem_Category;
    Button editItem_Save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

         editItemIntent= getIntent();

        initialiseTextFields();

        editItem_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest putRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Item.this, "The Request was: // " + response + " //", Toast.LENGTH_LONG).show();
                        Intent newIntent = new Intent(Edit_Item.this, MainActivity.class);
                        startActivity(newIntent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Edit_Item.this, "Error with Response! : "+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("\"currItemID\"", "\""+editItemIntent.getExtras().getString("itemID")+"\"");
                        params.put("\"newItemID\"", "\""+editItem_ID.getText().toString()+"\"");
                        params.put("\"itemName\"", "\""+editItem_Name.getText().toString()+"\"");
                        params.put("\"itemCost\"", "\""+editItem_Cost.getText().toString()+"\"");
                        params.put("\"itemCategory\"", "\""+editItem_Category.getText().toString()+"\"");

                        return params;
                    }
                };
                MySingleton.getInstance(Edit_Item.this).addToRequestQueue(putRequest);
            }
        });

    }

    public void initialiseTextFields() {
        editItem_ID = findViewById(R.id.ItemEdit_ID);
        editItem_Name = findViewById(R.id.ItemEdit_Name);
        editItem_Cost = findViewById(R.id.ItemEdit_Cost);
        editItem_Category = findViewById(R.id.ItemEdit_Category);
        editItem_Save = findViewById(R.id.ItemEdit_Save);

        String itemID = editItemIntent.getExtras().getString("itemID");
        String itemName = editItemIntent.getExtras().getString("itemName");
        String itemCost = editItemIntent.getExtras().getString("itemCost");
        String itemCategory = editItemIntent.getExtras().getString("itemCategory");

        editItem_ID.setText(itemID);
        editItem_Name.setText(itemName);
        editItem_Cost.setText(itemCost);
        editItem_Category.setText(itemCategory);
    }
}