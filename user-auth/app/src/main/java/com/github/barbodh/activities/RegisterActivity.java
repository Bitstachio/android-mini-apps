package com.github.barbodh.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barbodh.R;
import com.github.barbodh.utils.auth.CredentialManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Activity for handling user registration.
 * <p>
 * Provides input fields for user details such as first name, last name,
 * date of birth, username, email, and password. Includes validation
 * for empty fields, email format, duplicate usernames, and password strength.
 * <p>
 * On successful registration, the user is redirected to {@link WelcomeActivity}.
 */
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

    // =========================
    // Password Strength Regex
    // =========================

    private static final Pattern PASSWORD_HAS_UPPERCASE = Pattern.compile("[A-Z]");
    private static final Pattern PASSWORD_HAS_LOWERCASE = Pattern.compile("[a-z]");
    private static final Pattern PASSWORD_HAS_DIGIT = Pattern.compile("[0-9]");

    /**
     * Regex pattern requiring at least one special character in password.
     */
    private static final Pattern PASSWORD_HAS_SPECIAL_CHAR =
            Pattern.compile("[!@#$%^&*()_\\-+=\\[\\]{};':\"\\\\|,.<>/?~`]");

    // =========================
    // Lifecycle
    // =========================

    /**
     * Called when the activity is created.
     *
     * @param savedInstanceState previously saved state of the activity, or {@code null}
     */
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

    // =========================
    // Initialization
    // =========================

    /**
     * Binds XML views to their corresponding fields.
     * Also disables manual typing in the Date of Birth field
     * to force using the date picker.
     */
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

    /**
     * Sets up listeners for button clicks and the date picker.
     */
    private void setupListeners() {
        btnRegister.setOnClickListener(v -> handleRegister());

        tvLoginLink.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        etDob.setOnClickListener(v -> new DatePickerDialog(RegisterActivity.this,
                dateSetListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    // =========================
    // Utilities
    // =========================

    /**
     * Updates the Date of Birth field with the selected date
     * formatted as {@code MM/dd/yyyy}.
     */
    private void updateLabel() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etDob.setText(sdf.format(myCalendar.getTime()));
        etDob.setError(null);
    }

    /**
     * Validates whether a password meets strength requirements.
     *
     * @param password the password string to validate
     * @return {@code true} if password is strong, {@code false} otherwise
     */
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

    /**
     * Handles the registration process.
     * <p>
     * Validates all input fields, checks for duplicate usernames,
     * ensures passwords match, and verifies password strength.
     * On success, redirects to {@link WelcomeActivity}.
     */
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
        if (CredentialManager.isDuplicateUsername(username)) {
            etUsername.setError("This username is taken");
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

        registrationSuccess(username, password);
    }

    /**
     * Called after successful registration.
     * Displays a toast message and navigates to {@link WelcomeActivity}.
     *
     * @param username the registered username to pass to {@code CredentialManager} and the next activity
     * @param password the registered password to pass to {@code CredentialManager}
     */
    public void registrationSuccess(String username, String password) {
        CredentialManager.add(username, password);
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        CredentialManager.add(username, password);
    }
}
