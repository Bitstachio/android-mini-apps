package com.github.bitstachio.contactmanager.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.github.bitstachio.contactmanager.R;
import com.github.bitstachio.contactmanager.db.MockDatabase;
import com.github.bitstachio.contactmanager.model.Contact;
import com.google.android.material.appbar.MaterialToolbar;

public class ContactFormActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT_INDEX = "contact_index";

    private int contactIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText firstNameEditText = findViewById(R.id.firstNameEditText);
        EditText lastNameEditText = findViewById(R.id.lastNameEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText birthDateEditText = findViewById(R.id.birthDateEditText);
        EditText notesEditText = findViewById(R.id.notesEditText);
        Button saveButton = findViewById(R.id.saveButton);

        contactIndex = getIntent().getIntExtra(EXTRA_CONTACT_INDEX, -1);

        if (contactIndex != -1) {
            getSupportActionBar().setTitle("Edit Contact");
            Contact contact = MockDatabase.getContacts().get(contactIndex);
            firstNameEditText.setText(contact.getFirstName());
            lastNameEditText.setText(contact.getLastName());
            phoneEditText.setText(contact.getPhone());
            emailEditText.setText(contact.getEmail());
            birthDateEditText.setText(contact.getBirthday());
            notesEditText.setText(contact.getNotes());
        } else {
            getSupportActionBar().setTitle("New Contact");
        }

        saveButton.setOnClickListener(v -> {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String birthDate = birthDateEditText.getText().toString();
            String notes = notesEditText.getText().toString();

            Contact contact = new Contact(firstName, lastName, phone, email, birthDate, notes);

            if (contactIndex != -1) {
//                MockDatabase.updateContact(contactIndex, contact);
            } else {
//                MockDatabase.addContact(contact);
            }

            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
