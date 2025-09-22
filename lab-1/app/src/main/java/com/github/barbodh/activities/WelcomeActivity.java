package com.github.barbodh.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barbodh.R;

public class WelcomeActivity extends AppCompatActivity {

    // =========================
    // Widgets
    // =========================

    private TextView tvWelcomeMessage;

    // =========================
    // Initializers
    // =========================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
    }

    private void initViews() {
        tvWelcomeMessage = findViewById(R.id.tv_welcome_message);

        Intent intent = getIntent();
        String message = "Welcome " + intent.getStringExtra("username");
        tvWelcomeMessage.setText(message);
    }
}