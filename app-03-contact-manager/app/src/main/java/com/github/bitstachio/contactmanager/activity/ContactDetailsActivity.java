package com.github.bitstachio.contactmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.bitstachio.contactmanager.R;
import com.github.bitstachio.contactmanager.db.MockDatabase;
import com.github.bitstachio.contactmanager.model.Contact;
import com.google.android.material.appbar.MaterialToolbar;

public class ContactDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT_INDEX = "contact_index";
    private int contactIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Navigate to previous activity

        contactIndex = getIntent().getIntExtra(EXTRA_CONTACT_INDEX, -1);
        if (contactIndex == -1) {
            // TODO: What should be displayed?
            finish();
            return;
        }

        Contact contact = MockDatabase.getContacts().get(contactIndex);

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView birthDateTextView = findViewById(R.id.birthDateTextView);
        TextView notesTextView = findViewById(R.id.notesTextView);

        nameTextView.setText(String.format("%s %s", contact.getFirstName(), contact.getLastName()));
        phoneTextView.setText(contact.getPhone());
        emailTextView.setText(contact.getEmail());
        birthDateTextView.setText(contact.getBirthday());
        notesTextView.setText(contact.getNotes());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_edit_contact) {
            Intent intent = new Intent(this, ContactFormActivity.class);
            intent.putExtra(ContactFormActivity.EXTRA_CONTACT_INDEX, contactIndex);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
