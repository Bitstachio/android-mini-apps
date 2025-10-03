package com.github.bitstachio.listdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.bitstachio.listdemo.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Notes:
        // What if title/description/imageResId is null?
        // Should we use Glide or Picasso or setImageURI()?

        Intent intent = getIntent();
        // getExtra for getting data from the ListActivity
        String title = intent.getStringExtra("title");
        int imageResId = intent.getIntExtra("imageResId", -1);
        String description = intent.getStringExtra("description");

        ImageView detailImage = findViewById(R.id.detail_image);
        TextView detailTitle = findViewById(R.id.detail_title);
        TextView detailDesc = findViewById(R.id.detail_desc);

        if (title != null) {
            detailTitle.setText(title);
        }

        if (description != null) {
            detailDesc.setText(description);
        }

        if (imageResId != -1) {
            detailImage.setImageResource(imageResId);
        }
    }
}