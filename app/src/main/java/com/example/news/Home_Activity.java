package com.example.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Home_Activity extends AppCompatActivity implements getdata.DataFetchedListener,BackGround.BackGroundListener {
    ProgressBar progressBar;
    CustomAdapter customAdapter;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ClickListener clickListener;
    List<NewsGetter> data;
    String imageUrl;
    ActionBarDrawerToggle actionBarDrawerToggle;
    BackGround backGround;
    getdata getData;
    getdata.DataFetchedListener dataFetchedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.main);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.RecyclerView);
        progressBar = findViewById(R.id.progress);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,null,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data = new ArrayList<>();
        getData = new getdata();
        new BackGround(progressBar,this,this).execute();

        clickListener = new ClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(),"The item is clicked",Toast.LENGTH_LONG).show();
            }
        };
        customAdapter = new CustomAdapter(this, data,imageUrl);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataFetched(List<NewsGetter> newData, String imageUrl) {
        progressBar.setVisibility(View.GONE);
        data = newData;
        this.imageUrl = imageUrl;
        customAdapter.setData(data,imageUrl);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(Home_Activity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataFetched(List<NewsGetter> data) {
        progressBar.setVisibility(View.GONE);
        this.data = data;
        if (!data.isEmpty()) {
            imageUrl = data.get(0).geturlToImage();
            customAdapter.setData(data, imageUrl);
            customAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(Home_Activity.this, "No data found", Toast.LENGTH_SHORT).show();
            Log.e("List is empty","List is empty"+data);
        }
    }
}




