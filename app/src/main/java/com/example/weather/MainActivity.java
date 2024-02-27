package com.example.weather;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView temperature , condition , city;
    EditText search;
    ListView listView;
    Button button;
    ImageView imageView;
    RecyclerView recyclerView;
    private String cityname="";
    public List<String> cities;
    private String apikey = "1562c3cfdf054a7daa0135543241102";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperature = (TextView)findViewById(R.id.temp);
        condition = (TextView)findViewById(R.id.con);
        search = (EditText)findViewById(R.id.search);
        button = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.image);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        city=(TextView)findViewById(R.id.city);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        cityname="Hyderabad";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String name = search.getText().toString();
                cityname=name;
                city.setText(cityname);
                String apiurl = "https://api.weatherapi.com/v1/forecast.json?key=1562c3cfdf054a7daa0135543241102&q="+cityname+"&days=1&aqi=no&alerts=no";
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(apiurl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        search.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);
                        city.setVisibility(View.VISIBLE);
                        temperature.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.VISIBLE);
                        condition.setVisibility(View.VISIBLE);
                       recyclerView.setVisibility(View.VISIBLE);
                        try {
                            JSONObject jsonObject = response.getJSONObject("current");
                            int t = jsonObject.getInt("temp_c");
                            JSONObject j1 = jsonObject.getJSONObject("condition");
                            String c = j1.getString("text");
                            String url = "https:"+j1.getString("icon");

                            temperature.setText(Integer.toString(t)+"ºC");
                            condition.setText(c);
                            Picasso.get().load(url).into(imageView);
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this,"City Not Found",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",error.toString());
                        Toast.makeText(getApplicationContext() , "Error Occurred" , Toast.LENGTH_LONG).show();
                    }
                });
                requestQueue.add(jsonObjectRequest);
                search.setText("");
            }
        });
        city.setText(cityname);
        String apiurl = "https://api.weatherapi.com/v1/forecast.json?key=1562c3cfdf054a7daa0135543241102&q="+cityname+"&days=1&aqi=no&alerts=no";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(apiurl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                search.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                city.setVisibility(View.VISIBLE);
                temperature.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                condition.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                try {
                    JSONObject jsonObject = response.getJSONObject("current");
                    int t = jsonObject.getInt("temp_c");
                    JSONObject j1 = jsonObject.getJSONObject("condition");
                    String c = j1.getString("text");
                    String url = "https:"+j1.getString("icon");
                    temperature.setText(Integer.toString(t)+"ºC");
                    condition.setText(c);
                    Picasso.get().load(url).into(imageView);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
                Toast.makeText(getApplicationContext() , "Error Occurred" , Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
        cities = new ArrayList<>();
        cities.add("Mumbai");
        cities.add("Kolkata");
        cities.add("New Delhi");
        cities.add("London");
        cities.add("Goa");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,cities);
        recyclerView.setAdapter(customAdapter);
    }
}