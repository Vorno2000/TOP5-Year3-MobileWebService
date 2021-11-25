package com.example.shoppinglistapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemListService {
    String url ="http://192.168.1.47:8080/AndroidWebService/rest/webservice/android/all";
    Context context;

    public ItemListService(Context context) {
        this.context = context;
    }

    public interface ItemListResponseListener {
        void onError(String message);
        void onResponse(List<ItemListModel> response);
    }

    public void getItemList(ItemListResponseListener itemListResponseListener) {
        List<ItemListModel> itemList = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if(response.length() != 0) {
                    for(int i = 0; i < response.length(); i++) {
                        try {
                            ItemListModel newItem = new ItemListModel();

                            newItem.setItemID(Integer.parseInt(response.getJSONObject(i).getString("itemID")));
                            newItem.setItemName(response.getJSONObject(i).getString("itemName"));
                            newItem.setItemCost(Float.parseFloat(response.getJSONObject(i).getString("itemCost")));
                            newItem.setItemCategory(response.getJSONObject(i).getString("itemCategory"));

                            itemList.add(newItem);
                        } catch (JSONException e) {
                            itemListResponseListener.onError("Error setting new Item List");
                        }
                    }
                    itemListResponseListener.onResponse(itemList);
                }
                else
                    itemListResponseListener.onError("Item List Response has 0 JSON parameters");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                itemListResponseListener.onError("Error with Item List Response: Server may not be running");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
