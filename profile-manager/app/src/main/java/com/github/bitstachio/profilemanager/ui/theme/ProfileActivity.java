package com.github.bitstachio.profilemanager.ui.theme;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.bitstachio.profilemanager.R;
import com.github.bitstachio.profilemanager.controller.MainController;
import com.github.bitstachio.profilemanager.model.ProfilePhoto;
import com.github.bitstachio.profilemanager.model.ProfileRepository;

import java.io.IOException;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements MainController.Delegate {

    private ImageView imageView;
    private TextView statusText;

    private MainController controller;
    private ProfileRepository repo;
    private ProfilePhoto model;

    private Uri pendingCameraOutputUri = null;

    // Gallery (GetContent)
    private final ActivityResultLauncher<String> pickImage =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri == null) {
                    onStatus("Gallery canceled");
                    return;
                }
                controller.deliverPhoto(uri);
            });

    // Camera (TakePicture to precreated Uri)
    private final ActivityResultLauncher<Uri> takePicture =
            registerForActivityResult(new ActivityResultContracts.TakePicture(), success -> {
                if (success && pendingCameraOutputUri != null) {
                    controller.deliverPhoto(pendingCameraOutputUri);
                } else {
                    onStatus("Camera canceled");
                }
            });

    // Request multiple permissions
    private final ActivityResultLauncher<String[]> requestPerms =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                    (Map<String, Boolean> result) -> {
                        boolean all = true;
                        for (Boolean granted : result.values()) { if (!granted) { all = false; break; } }
                        if (all) {
                            onStatus("Permissions granted");
                        } else {
                            onStatus("Permissions denied");
                            Toast.makeText(this, "Permission required to proceed", Toast.LENGTH_SHORT).show();
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.imageView);
        statusText = findViewById(R.id.statusText);

        controller = new MainController(getApplicationContext(), this);
        repo = new ProfileRepository(getApplicationContext());
        model = repo.load();

        if (savedInstanceState != null) {
            String s = savedInstanceState.getString("img_uri");
            if (s != null) model.setImageUri(Uri.parse(s));
        }

        renderModel();

        Button btnTake = findViewById(R.id.btnTakePhoto);
        Button btnPick = findViewById(R.id.btnPickPhoto);

        btnPick.setOnClickListener(v -> {
            String[] perms = controller.requiredGalleryPermissions();
            if (!controller.hasAllPermissions(perms)) {
                controller.requestPermissionsIfNeeded(perms);
                return;
            }
            pickImage.launch("image/*");
        });

        btnTake.setOnClickListener(v -> {
            String[] perms = controller.requiredCameraPermissions();
            if (!controller.hasAllPermissions(perms)) {
                controller.requestPermissionsIfNeeded(perms);
                return;
            }
            try {
                pendingCameraOutputUri = controller.createCameraOutputUri();
                takePicture.launch(pendingCameraOutputUri);
            } catch (IOException e) {
                onStatus("Failed to create image file");
                Toast.makeText(this, "Error preparing camera output", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void renderModel() {
        if (model.getImageUri() != null) {
            imageView.setImageURI(model.getImageUri());
            onStatus("Image loaded");
        } else {
            imageView.setImageDrawable(null);
            onStatus("Ready");
        }
    }

    @Override
    public void onNeedPermissions(String[] permissions) {
        requestPerms.launch(permissions);
    }

    @Override
    public void onStatus(String message) {
        statusText.setText(message);
    }

    @Override
    public void onNewPhotoUri(Uri uri) {
        model.setImageUri(uri);
        repo.save(model);
        renderModel();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle out) {
        super.onSaveInstanceState(out);
        if (model.getImageUri() != null) {
            out.putString("img_uri", model.getImageUri().toString());
        }
    }
}
