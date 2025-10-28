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

/**
 * Activity displayed after a successful login or registration.
 * <p>
 * Retrieves the username passed through the {@link Intent}
 * and displays a personalized welcome message to the user.
 */
public class WelcomeActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
    }

    // =========================
    // Initialization
    // =========================

    /**
     * Initializes the welcome message view and sets its text
     * based on the username passed via the launching {@link Intent}.
     * <p>
     * If no username is provided, the message will simply say "Welcome!".
     */
    private void initViews() {
        TextView tvWelcomeMessage = findViewById(R.id.tv_welcome_message);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String message = username != null
                ? "Welcome " + username + "!"
                : "Welcome!";
        tvWelcomeMessage.setText(message);
    }
}
