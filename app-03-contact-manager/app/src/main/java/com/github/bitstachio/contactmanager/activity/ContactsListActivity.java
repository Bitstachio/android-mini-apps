package com.github.bitstachio.contactmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.bitstachio.contactmanager.R;
import com.github.bitstachio.contactmanager.db.MockDatabase;
import com.github.bitstachio.contactmanager.model.Contact;

import java.util.ArrayList;

public class ContactsListActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
}
