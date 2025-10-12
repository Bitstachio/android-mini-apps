package com.github.bitstachio.contactmanager.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.bitstachio.contactmanager.R;
import com.github.bitstachio.contactmanager.adapter.ContactAdapter;
import com.github.bitstachio.contactmanager.db.MockDatabase;
import com.github.bitstachio.contactmanager.model.Contact;

import java.util.List;

public class ContactsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        // If your layout has a Toolbar with id=toolbar, keep these two lines.
        // Otherwise remove them or ensure the id exists.
         Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Pull data from your mock DB
        List<Contact> contacts = MockDatabase.getContacts();

        // Set adapter
        adapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }

    // DELETE this; it’s not needed when using DB:
    // private void setUpContactModels() { ... }
}
