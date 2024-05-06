package com.example.news;

import android.content.Context;
import android.os.AsyncTask;
import android.text.PrecomputedText;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class BackGround extends AsyncTask<Void,Void,List<NewsGetter>> {
    ProgressBar progressBar;
    BackGroundListener listener;
    Context context;
    getdata.DataFetchedListener dataFetchedListener;
    public BackGround(ProgressBar progressBar, Context context, getdata.DataFetchedListener dataFetchedListener)
    {
        this.progressBar=progressBar;
        this.context = context;
        this.listener = (BackGroundListener)context;
        this.dataFetchedListener=dataFetchedListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected List<NewsGetter> doInBackground(Void... voids) {
        getdata GetData = new getdata();

        return GetData.fetchData(dataFetchedListener);
    }

    @Override
    protected void onPostExecute(List<NewsGetter> newsGetters) {
        super.onPostExecute(newsGetters);
        progressBar.setVisibility(View.GONE);
        listener.onDataFetched(newsGetters);
    }
    public interface BackGroundListener
    {
        void onDataFetched(List<NewsGetter> data);
    }
}
