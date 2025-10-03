package com.github.bitstachio.listdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.bitstachio.listdemo.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button testButton = findViewById(R.id.test_button);
        testButton.setOnClickListener(v -> {
            Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
            // putExtra for passing data to the DetailsActivity
            intent.putExtra("title", "Test Title");
            intent.putExtra("imageResId", R.drawable.ic_launcher_background);
            intent.putExtra("description", "This is a dummy description for testing purposes.");
            startActivity(intent);
        });
    }
}