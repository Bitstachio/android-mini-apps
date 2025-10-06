package com.github.bitstachio.listdemo.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.bitstachio.listdemo.R;

import java.util.ArrayList;

public class List_RecyclerViewAdapter extends RecyclerView.Adapter<List_RecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Player> listModels;

    public List_RecyclerViewAdapter(Context context, ArrayList<Player> listModels){
        this.context = context;
        this.listModels = listModels;

    }

    @NonNull
    @Override
    public List_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this is where we inflate the layout.
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new List_RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull List_RecyclerViewAdapter.ViewHolder holder, int position) {
        Player item = listModels.get(position);

        holder.tvTitle.setText(item.getName());
        holder.tvDescription.setText(item.getPosition());
        holder.imageView.setImageResource(item.getThumbnailResId());

        // handle click on this row and sends the relevant information to DetailsActivity class
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("title", item.getName());
            intent.putExtra("imageResId", item.getImageResId());
            intent.putExtra("description", item.getPosition());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return listModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle, tvDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivThumb);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
