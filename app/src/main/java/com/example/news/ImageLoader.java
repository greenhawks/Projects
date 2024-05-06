package com.example.news;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoader {
    public static void LoadImage(Context context, String url, ImageView imageView)
    {
        if(url!=null &&!url.isEmpty())
        {
            Picasso.get().load(url).into(imageView);
        }
    }
}
