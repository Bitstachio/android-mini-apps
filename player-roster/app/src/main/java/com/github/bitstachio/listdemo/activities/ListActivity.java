package com.github.bitstachio.listdemo.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.bitstachio.listdemo.R;

import java.util.ArrayList;

/**
 * Displays a scrollable list of {@link Player} items using a {@link RecyclerView}.
 * <p>
 * This activity retrieves player data from {@link MockDatabase}, initializes
 * the adapter, and binds the data to the RecyclerView layout.
 */
public class ListActivity extends AppCompatActivity {

    private ArrayList<Player> listModels = new ArrayList<>();

    /**
     * Temporary image placeholders used for testing or default visuals.
     */
    private final int[] listImages = {
            R.drawable.number1, R.drawable.number2, R.drawable.number3,
            R.drawable.number4, R.drawable.number5, R.drawable.number6,
            R.drawable.number7, R.drawable.number8, R.drawable.number9,
            R.drawable.number10, R.drawable.number11, R.drawable.number12,
            R.drawable.number13, R.drawable.number14, R.drawable.number15
    };

    /**
     * Initializes the activity, loads player data, and sets up the RecyclerView.
     *
     * @param savedInstanceState the saved instance state bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        // Handle window insets for edge-to-edge layout compatibility
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);

        // Load player data from the mock database
        listModels = MockDatabase.getPlayers();

        // Configure adapter and layout manager for the RecyclerView
        List_RecyclerViewAdapter adapter = new List_RecyclerViewAdapter(this, listModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
