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
import com.github.bitstachio.contactmanager.persistence.MockDatabase;
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

    // DELETE this; it’s not needed when using DB:
    // private void setUpContactModels() { ... }
}
