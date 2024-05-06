package com.example.news;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsGetter implements Parcelable {


    public static final Creator<NewsGetter> CREATOR = new Creator<NewsGetter>() {
        @Override
        public NewsGetter createFromParcel(Parcel in) {
            return new NewsGetter(in);
        }

        @Override
        public NewsGetter[] newArray(int size) {
            return new NewsGetter[size];
        }
    };
    @SerializedName("source")
    @Expose
    public source Source;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("urlToImage")
    @Expose
    public String urlToImage;
    protected NewsGetter(Parcel in) {
        Source = in.readParcelable(source.class.getClassLoader());
        author=in.readString();
        title=in.readString();
        description=in.readString();
        url=in.readString();
        urlToImage=in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public source getSource()
    {
        return Source;
    }
    public void setSource(source Source)
    {
        this.Source=Source;
    }
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author=author;
    }
    public String getTitle()
    {
        return this.title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String description)
    {
        this.description=description;
    }
    public String getUrl()
    {
        return this.url;
    }
    public void set(String url)
    {
        this.url=url;
    }
    public String geturlToImage()
    {
        return this.urlToImage;
    }
    public void setImageUrl(String imageUrl)
    {
        this.urlToImage=imageUrl;
    }
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(Source,i);
        parcel.writeString(author);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeString(urlToImage);
    }
}
