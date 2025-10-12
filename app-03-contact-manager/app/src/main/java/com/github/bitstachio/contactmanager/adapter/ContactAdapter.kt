package com.github.bitstachio.contactmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.bitstachio.contactmanager.R
import com.github.bitstachio.contactmanager.model.Contact


class ContactAdapter(
    private val data: List<Contact>
): RecyclerView.Adapter<ContactAdapter.ItemViewHolder>() {




    inner class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.textViewName)
        val phone: TextView = view.findViewById(R.id.textViewPhone)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflatedView: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)
        return ItemViewHolder(inflatedView)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val contactDb: Contact = data[position]
        holder.name.text = contactDb.firstName
        holder.phone.text = contactDb.phone
    }

    override fun getItemCount(): Int {
        return data.size
    }


}