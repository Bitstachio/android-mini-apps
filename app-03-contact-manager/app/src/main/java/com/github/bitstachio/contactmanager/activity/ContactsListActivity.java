package com.github.bitstachio.contactmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.bitstachio.contactmanager.R;
import com.github.bitstachio.contactmanager.adapter.ContactAdapter;
import com.github.bitstachio.contactmanager.model.Contact;
import com.github.bitstachio.contactmanager.persistence.PersistenceStrategy;
import com.github.bitstachio.contactmanager.persistence.service.ContactService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ContactsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private ContactService contactService;
    private Executor executor = Executors.newSingleThreadExecutor(); // Background thread

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactService = new ContactService(this);

        loadContacts(); // Load contacts off the main thread
    }

    private void loadContacts() {
        executor.execute(() -> {
            // Fetch contacts from Room and SharedPreferences
            List<Contact> contacts = new ArrayList<>(contactService.getAll(PersistenceStrategy.ROOM));
            contacts.addAll(contactService.getAll(PersistenceStrategy.SHARED_PREFS));

            // Update RecyclerView on the main thread
            runOnUiThread(() -> {
                adapter = new ContactAdapter(contacts);
                recyclerView.setAdapter(adapter);
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contacts_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_contact) {
            // Open the new contact page
            Intent intent = new Intent(this, ContactFormActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
