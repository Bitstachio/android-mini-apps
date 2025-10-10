package com.github.bitstachio.contactmanager.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.bitstachio.contactmanager.R;
import com.github.bitstachio.contactmanager.db.MockDatabase;
import com.github.bitstachio.contactmanager.model.Contact;

public class ContactDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT_INDEX = "contact_index";

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

        int contactIndex = getIntent().getIntExtra(EXTRA_CONTACT_INDEX, -1);
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
}
