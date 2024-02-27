package com.example.weather;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView city,temp,condition;
    ImageView imageView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        city=(TextView)itemView.findViewById(R.id.city);
        temp=(TextView)itemView.findViewById(R.id.temperature);
        condition=(TextView)itemView.findViewById(R.id.tcondition);
        imageView=(ImageView)itemView.findViewById(R.id.tcon);
    }

}
