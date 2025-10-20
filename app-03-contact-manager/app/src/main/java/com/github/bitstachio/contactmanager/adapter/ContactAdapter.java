package com.github.bitstachio.contactmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.bitstachio.contactmanager.R;
import com.github.bitstachio.contactmanager.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ItemViewHolder> {

    private final List<Contact> data;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Contact contact);
    }

    public ContactAdapter(List<Contact> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phone;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            phone = itemView.findViewById(R.id.textViewPhone);
        }

        public void bind(Contact contact, OnItemClickListener listener) {
            name.setText(contact.getFirstName() + " " + contact.getLastName());
            phone.setText(contact.getPhone());

            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onItemClick(contact);
            });
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row, parent, false);
        return new ItemViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Contact contactDb = data.get(position);
        holder.bind(contactDb, listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
