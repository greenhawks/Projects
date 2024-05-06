package com.example.news;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Articles {

    @SerializedName("articles")
    @Expose
    private List<NewsGetter> articles = null;

    public List<NewsGetter> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsGetter> articles) {
        this.articles = articles;
    }
}

