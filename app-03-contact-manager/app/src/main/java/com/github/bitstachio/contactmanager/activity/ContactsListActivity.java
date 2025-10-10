package com.github.bitstachio.contactmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.bitstachio.contactmanager.R;
import com.github.bitstachio.contactmanager.db.MockDatabase;
import com.github.bitstachio.contactmanager.model.Contact;

import java.util.ArrayList;

public class ContactsListActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contacts = MockDatabase.getContacts();

        // This is for testing purposes only
        Button detailsButton = findViewById(R.id.detailsButton);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int aliceIndex = -1;
                for (int i = 0; i < contacts.size(); i++) {
                    Contact contact = contacts.get(i);
                    // TODO: replace with id-based lookup
                    if (contact.getFirstName().equals("Alice") && contact.getLastName().equals("Johnson")) {
                        aliceIndex = i;
                        break;
                    }
                }

                if (aliceIndex != -1) {
                    Intent intent = new Intent(ContactsListActivity.this, ContactDetailsActivity.class);
                    intent.putExtra(ContactDetailsActivity.EXTRA_CONTACT_INDEX, aliceIndex);
                    startActivity(intent);
                }
            }
        });
    }

    // Reference: https://developer.android.com/develop/ui/views/components/menus
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contacts_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_contact) {
            Intent intent = new Intent(this, ContactFormActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
