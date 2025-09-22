package com.github.barbodh.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
            Toast.makeText(this, "Login button clicked", Toast.LENGTH_SHORT).show();
        });

        btnCancel.setOnClickListener(this::handleCancel);

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    // =========================
    // Event Handlers
    // =========================

    private void handleLogin() {
        String email = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        if (email.isEmpty()) {
            etUsername.setError("Email is required");
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required");
        } else {
            // Call presenter, viewmodel, or API
        }
    }

    private void handleCancel(View view) {
        etUsername.setText("");
        etPassword.setText("");
    }

    // =========================
    // Validators
    // =========================

    private void validateUsername() {

    }

    private void validatePassword() {

    }
}