package com.example.news;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class source implements Parcelable {


    public static final Creator<source> CREATOR = new Creator<source>() {
        @Override
        public source createFromParcel(Parcel in) {
            return new source(in);
        }

        @Override
        public source[] newArray(int size) {
            return new source[size];
        }
    };
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("name")
    @Expose
    String name;

    protected source(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
    }
}
