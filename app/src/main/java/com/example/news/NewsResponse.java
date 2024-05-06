package com.example.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("totalResults") // Fix capitalization here
    @Expose
    public Integer totalResults;
    @SerializedName("articles")
    @Expose
    public List<NewsGetter> articles;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return this.totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsGetter> getArticles() {
        return this.articles;
    }

    public void setArticles(List<NewsGetter> articles) {
        this.articles = articles;
    }
}
