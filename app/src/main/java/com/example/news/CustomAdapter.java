package com.example.news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<NewsGetter> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private String imageUrl;


    CustomAdapter(Context context, List<NewsGetter> data, String imageUrl) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.imageUrl = imageUrl;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.api_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsGetter newsGetter = mData.get(position);
        holder.myTextView.setText(newsGetter.getTitle());
        holder.description.setText(newsGetter.getDescription());
        holder.name.setText(newsGetter.getSource().getName());
        if (newsGetter.geturlToImage() != null && !newsGetter.geturlToImage().isEmpty()) {
            Log.d("image",newsGetter.toString());
            Picasso.get().load(newsGetter.geturlToImage()).into(holder.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView,description,name;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
            description=itemView.findViewById(R.id.description);
            name=itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    NewsGetter getItem(int id) {
        return mData.get(id);
    }


    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setData(List<NewsGetter> newData, String imageUrl) {
        this.mData = newData;
        this.imageUrl = imageUrl;
        notifyDataSetChanged();
    }
}
