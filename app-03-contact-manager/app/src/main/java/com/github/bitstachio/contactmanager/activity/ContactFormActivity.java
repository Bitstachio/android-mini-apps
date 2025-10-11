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

        saveButton.setOnClickListener(v -> {
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String birthDate = birthDateEditText.getText().toString();
            String notes = notesEditText.getText().toString();

            Contact contact = new Contact(firstName, lastName, phone, email, birthDate, notes);
//            MockDatabase.addContact(contact);

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
