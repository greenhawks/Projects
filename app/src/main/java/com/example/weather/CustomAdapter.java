package com.example.weather;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<String> cities;
    private RequestQueue requestQueue;

    public CustomAdapter(Context context, List<String> cities) {
        this.cities = cities;
        requestQueue = Volley.newRequestQueue(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String city = cities.get(position);
        makeApiCall(city, holder);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    private void makeApiCall(String city, ViewHolder holder) {
        String apiurl = "https://api.weatherapi.com/v1/forecast.json?key=1562c3cfdf054a7daa0135543241102&q=" + city + "&days=1&aqi=no&alerts=no";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, apiurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("current");
                            int temp = jsonObject.getInt("temp_c");
                            JSONObject conditionObject = jsonObject.getJSONObject("condition");
                            String condition = conditionObject.getString("text");
                            String iconUrl = "https:" + conditionObject.getString("icon");
                            holder.city.setText(city);
                            holder.temp.setText(String.valueOf(temp));
                            holder.condition.setText(condition);
                            Picasso.get().load(iconUrl).into(holder.imageView);
                        } catch (JSONException e) {
                            Log.e("CustomAdapter", "JSON parsing error: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CustomAdapter", "API call failed: " + error.getMessage());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}
