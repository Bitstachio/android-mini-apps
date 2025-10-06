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

public class ListActivity extends AppCompatActivity {

    ArrayList<Player> listModels = new ArrayList<>();

    int[] listImages = {R.drawable.number1, R.drawable.number2, R.drawable.number3,
            R.drawable.number4, R.drawable.number5, R.drawable.number6,
            R.drawable.number7, R.drawable.number8, R.drawable.number9,
            R.drawable.number10, R.drawable.number11, R.drawable.number12,
            R.drawable.number13, R.drawable.number14, R.drawable.number15};

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

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);

        listModels = MockDatabase.getPlayers();

        List_RecyclerViewAdapter adapter = new List_RecyclerViewAdapter(this, listModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        Button testButton = findViewById(R.id.test_button);
//        testButton.setOnClickListener(v -> {
//            Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
//            // putExtra for passing data to the DetailsActivity
//            intent.putExtra("title", "Test Title");
//            intent.putExtra("imageResId", R.drawable.ic_launcher_background);
//            intent.putExtra("description", "This is a dummy description for testing purposes.");
//            startActivity(intent);
//        });
    }
}