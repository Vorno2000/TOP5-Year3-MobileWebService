/*package com.example.shoppinglistapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditCatalogService {
    String url ="http://192.168.1.47:8080/AndroidWebService/rest/android/all";
    Context context;
    JSONObject productInfo = null;

    public EditCatalogService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(JSONObject response) throws JSONException;
    }

    public void getProductInfo(String itemID, VolleyResponseListener volleyResponseListener) {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                boolean found = false;
                if(response.length() != 0) {
                    if(!itemID.equals("")) {
                        breakpoint:
                        for(int i = 0; i < response.length(); i++) {
                            try {
                                String compareJSN = response.getJSONObject(i).getString("itemID");

                                if(Integer.parseInt(compareJSN) == Integer.parseInt(itemID)) {
                                    found = true;
                                    productInfo = response.getJSONObject(i);
                                    break breakpoint;
                                }
                            } catch (JSONException e) {
                                volleyResponseListener.onError("Error caught reading item ID from JSON");
                            }
                        }
                        if(!found)
                            volleyResponseListener.onError("Item ID not found");
                        else {
                            try {
                                volleyResponseListener.onResponse(productInfo);
                            } catch (JSONException e) {
                                volleyResponseListener.onError("Unable to send Item Information Response");
                            }
                        }
                    }
                    else
                        volleyResponseListener.onError("Please enter a Item ID to search");
                }
                else
                    volleyResponseListener.onError("Server responded with 0 JSON parameters");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Error with Response: Server may not be running");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}*/


package com.example.shoppinglistapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class EditCatalogService {
    String url ="http://192.168.1.47:8080/AndroidWebService/rest/webservice/android/getitem/";
    Context context;
    JSONObject productInfo = null;

    public EditCatalogService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(JSONObject response) throws JSONException;
    }

    public void getProductInfo(String itemID, VolleyResponseListener volleyResponseListener) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url+itemID, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response != null) {
                    try {
                        volleyResponseListener.onResponse(response);
                    } catch (JSONException e) {
                        volleyResponseListener.onError("Could not return the Response item");
                    }
                }
                else {
                    volleyResponseListener.onError("Item does not exist");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Error with Response: Item not found or Server not running");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}

