package com.github.bitstachio.profilemanager.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

/**
 * Controller class that handles permission checking, camera output creation,
 * and delegation of photo events to the UI layer.
 * <p>
 * Acts as the bridge between the UI (ProfileActivity) and lower-level Android services.
 */
public class MainController {

    /**
     * Delegate interface to communicate status updates, permission requests,
     * and newly selected or captured photo URIs to the UI layer.
     */
    public interface Delegate {
        /**
         * Called when specific permissions are required.
         *
         * @param permissions Array of permissions to request
         */
        void onNeedPermissions(String[] permissions);

        /**
         * Called to update UI or log status messages.
         *
         * @param message Status message
         */
        void onStatus(String message);

        /**
         * Called when a new photo URI is available (from camera or gallery).
         *
         * @param uri URI of the new photo
         */
        void onNewPhotoUri(Uri uri);
    }

    private final Context appContext;
    private final Delegate delegate;

    /**
     * Constructs a MainController with the application context and a delegate.
     *
     * @param appContext Application context
     * @param delegate   Delegate to receive events
     */
    public MainController(Context appContext, Delegate delegate) {
        this.appContext = appContext.getApplicationContext();
        this.delegate = delegate;
    }

    /**
     * Returns the list of required permissions to access the gallery,
     * depending on the Android SDK version.
     *
     * @return Array of required gallery permissions
     */
    public String[] requiredGalleryPermissions() {
        if (Build.VERSION.SDK_INT >= 33) {
            return new String[]{Manifest.permission.READ_MEDIA_IMAGES};
        } else {
            return new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        }
    }

    /**
     * Returns the list of required permissions to use the camera,
     * including storage access for saving images.
     *
     * @return Array of required camera permissions
     */
    public String[] requiredCameraPermissions() {
        String read = (Build.VERSION.SDK_INT >= 33)
                ? Manifest.permission.READ_MEDIA_IMAGES
                : Manifest.permission.READ_EXTERNAL_STORAGE;
        return new String[]{Manifest.permission.CAMERA, read};
    }

    /**
     * Checks whether all specified permissions are granted.
     *
     * @param perms Array of permissions to check
     * @return true if all permissions are granted, false otherwise
     */
    public boolean hasAllPermissions(String[] perms) {
        for (String p : perms) {
            if (ContextCompat.checkSelfPermission(appContext, p)
                    != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }

    /**
     * Creates a temporary file in the cache directory and returns a content URI
     * for use with {@link android.provider.MediaStore#ACTION_IMAGE_CAPTURE}.
     *
     * @return Content URI pointing to the temporary camera output file
     * @throws IOException if file creation fails
     */
    public Uri createCameraOutputUri() throws IOException {
        File dir = new File(appContext.getCacheDir(), "images");
        if (!dir.exists()) dir.mkdirs();
        File f = File.createTempFile("camera_", ".jpg", dir);
        return FileProvider.getUriForFile(
                appContext,
                appContext.getPackageName() + ".fileprovider",
                f
        );
    }

    /**
     * Requests the specified permissions via the delegate if they are not yet granted.
     *
     * @param perms Array of permissions to request
     */
    public void requestPermissionsIfNeeded(String[] perms) {
        if (!hasAllPermissions(perms)) {
            delegate.onNeedPermissions(perms);
        }
    }

    /**
     * Publishes a status message via the delegate.
     *
     * @param msg Status message
     */
    public void publishStatus(String msg) {
        delegate.onStatus(msg);
    }

    /**
     * Delivers a newly obtained photo URI to the delegate.
     *
     * @param uri URI of the new photo
     */
    public void deliverPhoto(Uri uri) {
        delegate.onNewPhotoUri(uri);
    }
}
