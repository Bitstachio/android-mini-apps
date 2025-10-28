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

/**
 * Displays detailed information about a selected player.
 * <p>
 * This activity retrieves player data passed through an Intent,
 * and populates the UI with the player's name, position, description, and image.
 */
public class DetailsActivity extends AppCompatActivity {

    /**
     * Called when the activity is created.
     * <p>
     * Initializes the layout, applies window insets for edge-to-edge content,
     * retrieves player data from the intent, and binds it to the corresponding views.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down, this Bundle contains
     *                           the most recent data. Otherwise, it is null.
     */
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
        int id = intent.getIntExtra("id", 0);
        Player player = MockDatabase.getPlayerById(id);

        String name = "";
        String position = "";
        String description = "";
        int imageResId = -1;

        if (player != null) {
            name = player.getName();
            position = player.getPosition();
            description = player.getDescription();
            imageResId = player.getImageResId();
        }

        ImageView detailImage = findViewById(R.id.detail_image);
        TextView detailTitle = findViewById(R.id.detail_title);
        TextView detailPosition = findViewById(R.id.detail_position);
        TextView detailDesc = findViewById(R.id.detail_desc);

        if (name != null) {
            detailTitle.setText(name);
        }

        if (description != null) {
            detailDesc.setText(description);
        }

        if (detailPosition != null) {
            detailPosition.setText(position);
        }

        if (imageResId != -1) {
            detailImage.setImageResource(imageResId);
        }
    }
}
