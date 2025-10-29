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

public class MainController {

    public interface Delegate {
        void onNeedPermissions(String[] permissions);
        void onStatus(String message);
        void onNewPhotoUri(Uri uri);
    }

    private final Context appContext;
    private final Delegate delegate;

    public MainController(Context appContext, Delegate delegate) {
        this.appContext = appContext.getApplicationContext();
        this.delegate = delegate;
    }

    public String[] requiredGalleryPermissions() {
        if (Build.VERSION.SDK_INT >= 33) {
            return new String[]{ Manifest.permission.READ_MEDIA_IMAGES };
        } else {
            return new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE };
        }
    }

    public String[] requiredCameraPermissions() {
        String read = (Build.VERSION.SDK_INT >= 33)
                ? Manifest.permission.READ_MEDIA_IMAGES
                : Manifest.permission.READ_EXTERNAL_STORAGE;
        return new String[]{ Manifest.permission.CAMERA, read };
    }

    public boolean hasAllPermissions(String[] perms) {
        for (String p : perms) {
            if (ContextCompat.checkSelfPermission(appContext, p)
                    != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }

    /** Create a cache file and return a content:// Uri for ACTION_IMAGE_CAPTURE */
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

    public void requestPermissionsIfNeeded(String[] perms) {
        if (!hasAllPermissions(perms)) {
            delegate.onNeedPermissions(perms);
        }
    }

    public void publishStatus(String msg) { delegate.onStatus(msg); }

    public void deliverPhoto(Uri uri) { delegate.onNewPhotoUri(uri); }
}
