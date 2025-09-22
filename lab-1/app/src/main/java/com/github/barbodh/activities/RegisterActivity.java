package com.github.barbodh.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barbodh.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    // =========================
    // Widgets
    // =========================
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etDob;
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnRegister;
    private TextView tvLoginLink;

    private Calendar myCalendar;

    // Password Strength Regex Patterns
    private static final Pattern PASSWORD_HAS_UPPERCASE = Pattern.compile("[A-Z]");
    private static final Pattern PASSWORD_HAS_LOWERCASE = Pattern.compile("[a-z]");
    private static final Pattern PASSWORD_HAS_DIGIT = Pattern.compile("[0-9]");
    private static final Pattern PASSWORD_HAS_SPECIAL_CHAR = Pattern.compile("[!@#$%^&*()_\\-+=\\[\\]{};':\"\\\\|,.<>/?~`]");



    // =========================
    // Initializers
    // =========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myCalendar = Calendar.getInstance();
        initViews();
        setupListeners();
    }

    private void initViews() {
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etDob = findViewById(R.id.et_dob);
        etUsername = findViewById(R.id.et_username_register);
        etEmail = findViewById(R.id.et_email_register);
        etPassword = findViewById(R.id.et_password_register);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.btn_register);
        tvLoginLink = findViewById(R.id.tv_login_link);

        etDob.setFocusable(false);
        etDob.setClickable(true);
    }

    private void setupListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegister();
            }
        });

        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this,
                        dateSetListener,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etDob.setText(sdf.format(myCalendar.getTime()));
        etDob.setError(null);
    }

    private boolean isPasswordStrong(String password) {
        if (password.length() < 8) {
            etPassword.setError("Password must be at least 8 characters long.");
            return false;
        }
        if (!PASSWORD_HAS_UPPERCASE.matcher(password).find()) {
            etPassword.setError("Password must include at least one uppercase letter.");
            return false;
        }
        if (!PASSWORD_HAS_LOWERCASE.matcher(password).find()) {
            etPassword.setError("Password must include at least one lowercase letter.");
            return false;
        }
        if (!PASSWORD_HAS_DIGIT.matcher(password).find()) {
            etPassword.setError("Password must include at least one digit.");
            return false;
        }
        if (!PASSWORD_HAS_SPECIAL_CHAR.matcher(password).find()) {
            etPassword.setError("Password must include at least one special character (e.g., @#$%).");
            return false;
        }
        etPassword.setError(null);
        return true;
    }

    // =========================
    // Event Handlers
    // =========================
    private void handleRegister() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String dob = etDob.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (firstName.isEmpty()) {
            etFirstName.setError("First name is required");
            etFirstName.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            etLastName.setError("Last name is required");
            etLastName.requestFocus();
            return;
        }
        if (dob.isEmpty()) {
            etDob.setError("Date of birth is required");
            Toast.makeText(this, "Please select a date of birth", Toast.LENGTH_SHORT).show();
            return;
        }
        if (username.isEmpty()) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email address");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }
        if (!isPasswordStrong(password)) {
            etPassword.requestFocus();
            return;
        }
        if (confirmPassword.isEmpty()) {
            etConfirmPassword.setError("Confirm password is required");
            etConfirmPassword.requestFocus();
            return;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return;
        }

        registrationSuccess(username);
    }

    public void registrationSuccess(String username) {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
