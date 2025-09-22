package com.github.barbodh.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

/**
 * LoginActivity handles user authentication by providing a login form.
 * It validates credentials, supports a "Remember Me" feature with SharedPreferences,
 * and can skip login if the user previously chose to be remembered.
 */
public class LoginActivity extends AppCompatActivity {

    // =========================
    // Widgets
    // =========================

    private Button btnLogin;
    private Button btnCancel;
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvRegister;
    private TextView tvErrorMessage;

    // =========================
    // SharedPreferences Fields
    // =========================

    private SharedPreferences prefs;
    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER = "remember";

    // =========================
    // Initializers
    // =========================

    /**
     * Called when the activity is first created.
     * Initializes SharedPreferences, skips login if applicable, binds views, and sets listeners.
     *
     * @param savedInstanceState Saved activity state, if available
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        skipLoginIfApplicable();
        initViews();
        setupListeners();
    }

    /**
     * Skips the login screen if the user has previously selected "Remember Me".
     * If stored credentials are valid, proceeds to login success.
     */
    private void skipLoginIfApplicable() {
        boolean remember = prefs.getBoolean(KEY_REMEMBER, false);
        if (remember) {
            String username = prefs.getString(KEY_USERNAME, "");
            String password = prefs.getString(KEY_PASSWORD, "");
            boolean result = CredentialManager.validate(username, password);
            if (result) loginSuccess(username);
        }
    }

    /**
     * Binds the XML layout widgets to Java fields.
     */
    private void initViews() {
        btnLogin = findViewById(R.id.btn_login);
        btnCancel = findViewById(R.id.btn_cancel);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        tvRegister = findViewById(R.id.tvRegister);
        tvErrorMessage = findViewById(R.id.tv_error_message);
    }

    /**
     * Sets up listeners for buttons, checkboxes, and clickable text.
     */
    private void setupListeners() {
        btnLogin.setOnClickListener(v -> handleLogin());

        btnCancel.setOnClickListener(this::handleCancel);

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        CheckBox checkboxShowPassword = findViewById(R.id.checkbox_show_password);
        checkboxShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            etPassword.setSelection(etPassword.getText().length()); // keep cursor at end
        });
    }

    // =========================
    // Event Handlers
    // =========================

    /**
     * Handles login logic:
     * <ul>
     *   <li>Validates input fields</li>
     *   <li>Validates credentials against {@link CredentialManager}</li>
     *   <li>Saves or clears credentials based on "Remember Me"</li>
     *   <li>Shows success or failure state</li>
     * </ul>
     */
    private void handleLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        if (username.isEmpty()) {
            etUsername.setError("Username is required");
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required");
        } else {
            boolean result = CredentialManager.validate(
                    etUsername.getText().toString().trim(),
                    etPassword.getText().toString().trim()
            );

            if (result) {
                CheckBox checkboxRememberMe = findViewById(R.id.checkboxRememberMe);
                SharedPreferences.Editor editor = prefs.edit();

                if (checkboxRememberMe.isChecked()) {
                    editor.putString(KEY_USERNAME, username);
                    editor.putString(KEY_PASSWORD, password);
                    editor.putBoolean(KEY_REMEMBER, true);
                } else {
                    editor.clear(); // wipe stored credentials
                }
                editor.apply();

                loginSuccess(username);
            } else {
                loginFail();
            }
        }
    }

    /**
     * Handles the Cancel button click by clearing input fields.
     *
     * @param view The Cancel button
     */
    private void handleCancel(View view) {
        etUsername.setText("");
        etPassword.setText("");
    }

    // =========================
    // Utilities
    // =========================

    /**
     * Called on successful login.
     * Displays a Toast and launches {@link WelcomeActivity}.
     *
     * @param username The logged-in username
     */
    private void loginSuccess(String username) {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    /**
     * Called on failed login.
     * Displays an error message TextView.
     */
    private void loginFail() {
        tvErrorMessage.setVisibility(View.VISIBLE);
    }
}
