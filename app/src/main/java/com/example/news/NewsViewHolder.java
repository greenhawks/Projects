package com.example.news;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    TextView title,description,name;
    ImageView imageView;
    View view;
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        title=(TextView)itemView.findViewById(R.id.title);
        description=(TextView)itemView.findViewById(R.id.description);
        name = (TextView)itemView.findViewById(R.id.name);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        view=itemView;
    }
}
