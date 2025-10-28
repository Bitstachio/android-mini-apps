package com.github.bitstachio.profilemanager.model;

import android.net.Uri;

public class ProfilePhoto {
    private Uri imageUri;

    public ProfilePhoto(Uri imageUri) { this.imageUri = imageUri; }
    public Uri getImageUri() { return imageUri; }
    public void setImageUri(Uri imageUri) { this.imageUri = imageUri; }
}
