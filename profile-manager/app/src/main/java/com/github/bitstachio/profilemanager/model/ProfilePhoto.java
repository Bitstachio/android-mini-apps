package com.github.bitstachio.profilemanager.model;

import android.net.Uri;

/**
 * Model class representing a user's profile photo.
 * <p>
 * Stores a single {@link Uri} pointing to the profile image.
 * Provides getters and setters for accessing and updating the image URI.
 */
public class ProfilePhoto {

    /**
     * URI of the profile image
     */
    private Uri imageUri;

    /**
     * Constructs a ProfilePhoto with the given image URI.
     *
     * @param imageUri URI of the profile image, may be null if no image is set
     */
    public ProfilePhoto(Uri imageUri) {
        this.imageUri = imageUri;
    }

    /**
     * Returns the URI of the profile image.
     *
     * @return URI of the image, or null if none is set
     */
    public Uri getImageUri() {
        return imageUri;
    }

    /**
     * Sets or updates the URI of the profile image.
     *
     * @param imageUri New URI of the profile image, may be null to clear the image
     */
    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
