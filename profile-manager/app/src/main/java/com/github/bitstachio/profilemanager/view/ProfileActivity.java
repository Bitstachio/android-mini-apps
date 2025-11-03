package com.github.bitstachio.profilemanager.view;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.bitstachio.profilemanager.R;
import com.github.bitstachio.profilemanager.controller.MainController;
import com.github.bitstachio.profilemanager.model.ProfilePhoto;
import com.github.bitstachio.profilemanager.model.ProfileRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Activity for managing the user's profile photo.
 * <p>
 * Allows users to take a photo, pick an image from the gallery, or clear the current image.
 * Ensures images are persisted in internal storage to prevent access issues on app restart.
 * Handles runtime permissions, orientation changes, and secure media access.
 */
public class ProfileActivity extends AppCompatActivity implements MainController.Delegate {

    private ImageView imageView;
    private TextView statusText;

    private MainController controller;
    private ProfileRepository repo;
    private ProfilePhoto model;

    private Uri pendingCameraOutputUri = null;

    /**
     * ActivityResultLauncher for picking images from the gallery.
     * Saves the selected image to internal storage before delivering it to the controller.
     */
    private final ActivityResultLauncher<PickVisualMediaRequest> pickImage =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri == null) {
                    onStatus("Gallery canceled");
                    return;
                }
                try {
                    Uri savedUri = copyToInternalStorage(uri);
                    controller.deliverPhoto(savedUri);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
                    onStatus("Failed to save image");
                }
            });

    /**
     * ActivityResultLauncher for taking a photo using the device camera.
     * The photo is stored at a pre-created Uri.
     */
    private final ActivityResultLauncher<Uri> takePicture =
            registerForActivityResult(new ActivityResultContracts.TakePicture(), success -> {
                if (success && pendingCameraOutputUri != null) {
                    controller.deliverPhoto(pendingCameraOutputUri);
                } else {
                    onStatus("Camera canceled");
                }
            });

    /**
     * ActivityResultLauncher for requesting multiple runtime permissions.
     * Updates status and shows a toast if permissions are denied.
     */
    private final ActivityResultLauncher<String[]> requestPerms =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                    (Map<String, Boolean> result) -> {
                        boolean all = true;
                        for (Boolean granted : result.values()) {
                            if (!granted) {
                                all = false;
                                break;
                            }
                        }
                        if (all) {
                            onStatus("Permissions granted");
                        } else {
                            onStatus("Permissions denied");
                            Toast.makeText(this, "Permission required to proceed", Toast.LENGTH_SHORT).show();
                        }
                    });

    /**
     * Initializes the activity, sets up UI elements, and restores any saved image URI.
     *
     * @param savedInstanceState Bundle containing saved state from a previous instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.imageView);
        statusText = findViewById(R.id.statusText);

        controller = new MainController(getApplicationContext(), this);
        repo = new ProfileRepository(getApplicationContext());
        model = repo.load();

        // Restore saved URI safely
        if (savedInstanceState != null) {
            String s = savedInstanceState.getString("img_uri");
            if (s != null) model.setImageUri(Uri.parse(s));
        }

        renderModel();

        Button btnTake = findViewById(R.id.btnTakePhoto);
        Button btnPick = findViewById(R.id.btnPickPhoto);
        Button btnClear = findViewById(R.id.btnClearPhoto);

        // Launch Photo Picker
        btnPick.setOnClickListener(v -> {
            String[] perms = controller.requiredGalleryPermissions();
            if (!controller.hasAllPermissions(perms)) {
                controller.requestPermissionsIfNeeded(perms);
                return;
            }
            pickImage.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        });

        // Launch Camera
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

        // Clear image
        btnClear.setOnClickListener(v -> {
            model.setImageUri(null);
            repo.save(model);
            imageView.setImageDrawable(null);
            onStatus("Image cleared");
            Toast.makeText(this, "Image cleared", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Copies a selected gallery image to internal storage to ensure persistent access.
     *
     * @param sourceUri Uri of the selected image
     * @return Uri pointing to the internally saved copy
     * @throws IOException if reading or writing fails
     */
    private Uri copyToInternalStorage(Uri sourceUri) throws IOException {
        ContentResolver resolver = getContentResolver();
        File destFile = new File(getFilesDir(), "profile_photo.jpg");

        try (InputStream in = resolver.openInputStream(sourceUri);
             FileOutputStream out = new FileOutputStream(destFile)) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
        return Uri.fromFile(destFile);
    }

    /**
     * Updates the UI to display the current image in the model.
     * Handles SecurityException if the image URI is no longer accessible.
     */
    private void renderModel() {
        if (model.getImageUri() != null) {
            try {
                imageView.setImageURI(model.getImageUri());
                onStatus("Image loaded");
            } catch (SecurityException e) {
                model.setImageUri(null);
                repo.save(model);
                imageView.setImageDrawable(null);
                onStatus("Image access lost — please reselect");
                Toast.makeText(this, "Image access lost — please reselect", Toast.LENGTH_SHORT).show();
            }
        } else {
            imageView.setImageDrawable(null);
            onStatus("Ready");
        }
    }

    /**
     * Delegate callback for requesting runtime permissions.
     *
     * @param permissions Array of permissions that need to be requested
     */
    @Override
    public void onNeedPermissions(String[] permissions) {
        requestPerms.launch(permissions);
    }

    /**
     * Updates the status TextView with a message.
     *
     * @param message Message to display in the UI
     */
    @Override
    public void onStatus(String message) {
        statusText.setText(message);
    }

    /**
     * Delegate callback when a new photo URI is available.
     * Saves the URI in the repository and refreshes the UI.
     *
     * @param uri Uri of the newly selected or captured image
     */
    @Override
    public void onNewPhotoUri(Uri uri) {
        model.setImageUri(uri);
        repo.save(model);
        renderModel();
    }

    /**
     * Saves the current image URI to the instance state bundle to survive
     * configuration changes such as orientation change.
     *
     * @param out Bundle to save state
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle out) {
        super.onSaveInstanceState(out);
        if (model.getImageUri() != null) {
            out.putString("img_uri", model.getImageUri().toString());
        }
    }
}
