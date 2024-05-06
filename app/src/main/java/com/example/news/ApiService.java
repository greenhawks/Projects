package com.example.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("top-headlines")
    Call<NewsResponse> gettopheadlines(
            @Query("country") String country,
            @Query("apikey") String apikey

    );

}
