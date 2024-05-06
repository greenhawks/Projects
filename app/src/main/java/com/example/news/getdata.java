package com.example.news;

import android.os.Parcel;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class getdata {
    List<NewsGetter> data;
    Parcel parcel;

    public getdata() {
        data = new ArrayList<>();
    }

    public List<NewsGetter> fetchData(DataFetchedListener listener) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<NewsResponse> call = apiService.gettopheadlines("us", "d8407e963d714e97bc8c0efa4cf6e41e");
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<NewsGetter> articles = response.body().getArticles();
                    data.addAll(articles);
                    if (!articles.isEmpty()) {
                        String imageUrl = articles.get(0).geturlToImage();
                        listener.onDataFetched(data,imageUrl);
                        if(imageUrl != null) {
                            Log.e("Get Data", imageUrl + "is the url");
                        }
                        else
                        {
                            Log.e("Get Data","image not found");
                        }
                        Log.e("Articles data",articles.get(0).geturlToImage().toString());
                    }
                    else
                    {
                        Log.e("Get Data","Image not found");
                    }

                }
                else {
                    Log.e("Get Data","failed to fetch data");
                    listener.onError("Failed to Fetch");
                }


            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("error","Network Error");
            }
        });

    return data;
    }
    public interface DataFetchedListener {
        void onDataFetched(List<NewsGetter> data, String imageUrl);

        void onError(String message);
    }
}
