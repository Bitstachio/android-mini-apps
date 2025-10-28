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

/**
 * Adapter for displaying a list of {@link Player} items in a RecyclerView.
 * <p>
 * This class binds player data (name, position, and thumbnail) to list items,
 * and handles click events to open the {@link DetailsActivity} for each player.
 */
public class List_RecyclerViewAdapter extends RecyclerView.Adapter<List_RecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Player> listModels;

    /**
     * Constructs a new adapter with the provided context and list of players.
     *
     * @param context    the current context
     * @param listModels the list of {@link Player} objects to display
     */
    public List_RecyclerViewAdapter(Context context, ArrayList<Player> listModels) {
        this.context = context;
        this.listModels = listModels;
    }

    /**
     * Inflates the layout for each row in the RecyclerView.
     *
     * @param parent   the parent ViewGroup
     * @param viewType the view type of the new View
     * @return a new {@link ViewHolder} instance
     */
    @NonNull
    @Override
    public List_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new List_RecyclerViewAdapter.ViewHolder(view);
    }

    /**
     * Binds data from a {@link Player} object to the corresponding row views.
     *
     * @param holder   the {@link ViewHolder} for the current item
     * @param position the position of the item in the list
     */
    @Override
    public void onBindViewHolder(@NonNull List_RecyclerViewAdapter.ViewHolder holder, int position) {
        Player item = listModels.get(position);

        holder.tvTitle.setText(item.getName());
        holder.tvDescription.setText(item.getPosition());
        holder.imageView.setImageResource(item.getThumbnailResId());

        // Handle click on this row to launch the details screen
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("id", item.getId());
            context.startActivity(intent);
        });
    }

    /**
     * Returns the total number of items in the list.
     *
     * @return the item count
     */
    @Override
    public int getItemCount() {
        return listModels.size();
    }

    /**
     * ViewHolder class that holds references to each view in a list item layout.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle, tvDescription;

        /**
         * Creates a new ViewHolder instance and binds its views.
         *
         * @param itemView the root view of the list item layout
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivThumb);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
