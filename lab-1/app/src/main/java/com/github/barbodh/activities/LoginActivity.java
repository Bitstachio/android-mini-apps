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

public class LoginActivity extends AppCompatActivity {

    // =========================
    // Widgets
    // =========================

    private Button btnLogin;
    private Button btnCancel;
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvRegister;

    // =========================
    // Initializers
    // =========================

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

        initViews();
        setupListeners();
    }

    private void initViews() {
        btnLogin = findViewById(R.id.btn_login);
        btnCancel = findViewById(R.id.btn_cancel);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        tvRegister = findViewById(R.id.tvRegister);
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(v -> handleLogin());
        // TODO: This toast is used for debugging; remove later
        btnLogin.setOnClickListener(v -> {
            handleLogin();
        });

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

    private void handleLogin() {
        String email = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        if (email.isEmpty()) {
            etUsername.setError("Username is required");
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required");
        } else {
            boolean result = CredentialManager.validate(
                    etUsername.getText().toString().trim(),
                    etPassword.getText().toString().trim()
            );

            if (result) Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleCancel(View view) {
        etUsername.setText("");
        etPassword.setText("");
    }
}