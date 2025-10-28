package com.github.bitstachio.contactmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.github.bitstachio.contactmanager.R;
import com.github.bitstachio.contactmanager.model.Contact;
import com.github.bitstachio.contactmanager.persistence.PersistenceStrategy;
import com.github.bitstachio.contactmanager.persistence.service.ContactService;
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
        Contact contact = (Contact) getIntent().getSerializableExtra("contact");

        Log.d("NewContactForm", contact != null ? contact.toString() : "NULL");

        if (contact != null) {
            getSupportActionBar().setTitle("Edit Contact");
            firstNameEditText.setText(contact.getFirstName());
            lastNameEditText.setText(contact.getLastName());
            phoneEditText.setText(contact.getPhone());
            emailEditText.setText(contact.getEmail());
            birthDateEditText.setText(contact.getBirthday());
            notesEditText.setText(contact.getNotes());
        } else {
            getSupportActionBar().setTitle("New Contact");
            View view = findViewById(R.id.ll_storage_method);
            view.setVisibility(View.VISIBLE);
        }

        // TODO: Temporary service initialization
        ContactService contactService = new ContactService(this);

        saveButton.setOnClickListener(v -> {
            String firstName = firstNameEditText.getText().toString().trim();
            String lastName = lastNameEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String birthDate = birthDateEditText.getText().toString().trim();
            String notes = notesEditText.getText().toString().trim();

            if (firstName.isEmpty()) {
                firstNameEditText.setError("First name is required");
                firstNameEditText.requestFocus();
                return;
            }

            if (phone.isEmpty()) {
                phoneEditText.setError("Phone number is required");
                phoneEditText.requestFocus();
                return;
            }

            if (!email.isEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.setError("Invalid email address");
                emailEditText.requestFocus();
                return;
            }

            if (!birthDate.isEmpty() && !birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                birthDateEditText.setError("Use format: YYYY-MM-DD");
                birthDateEditText.requestFocus();
                return;
            }

            RadioGroup storageMethodRadioGroup = findViewById(R.id.storageMethodRadioGroup);
            int selectedId = storageMethodRadioGroup.getCheckedRadioButtonId();
            boolean isSqlite = contact == null ? selectedId == R.id.sqliteRadioButton : contact.isSqlite();

            Contact newContact = new Contact(
                    contact == null ? -1 : contact.getId(),
                    firstName,
                    lastName,
                    phone,
                    email,
                    birthDate,
                    notes,
                    isSqlite
            );

            new Thread(() -> {
                try {
                    PersistenceStrategy strategy = newContact.isSqlite()
                            ? PersistenceStrategy.ROOM
                            : PersistenceStrategy.SHARED_PREFS;

                    if (contact != null) {
                        contactService.update(strategy, newContact);
                    } else {
                        contactService.insert(strategy, newContact);
                    }

                    // Back to main thread after successful save
                    runOnUiThread(() -> {
                        Intent intent = new Intent(this, ContactsListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    });

                } catch (Exception e) {
                    Log.e("ContactForm", "Error saving contact", e);
                }
            }).start();

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
