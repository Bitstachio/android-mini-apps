package com.github.bitstachio.profilemanager.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

/**
 * Repository class for storing and retrieving the user's profile photo.
 * <p>
 * Uses {@link SharedPreferences} to persist the URI of the profile image
 * across app sessions.
 */
public class ProfileRepository {

    /**
     * Name of the SharedPreferences file
     */
    private static final String PREFS = "profile_repo";

    /**
     * Key used to store the profile image URI
     */
    private static final String KEY_URI = "profile_uri";

    /**
     * SharedPreferences instance
     */
    private final SharedPreferences prefs;

    /**
     * Constructs a ProfileRepository with the given application context.
     *
     * @param ctx Application context used to access SharedPreferences
     */
    public ProfileRepository(Context ctx) {
        prefs = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    /**
     * Saves the given ProfilePhoto to SharedPreferences.
     * <p>
     * If the image URI is null, the stored value is cleared.
     *
     * @param photo ProfilePhoto to save
     */
    public void save(ProfilePhoto photo) {
        prefs.edit()
                .putString(KEY_URI, photo.getImageUri() == null ? null : photo.getImageUri().toString())
                .apply();
    }

    /**
     * Loads the stored ProfilePhoto from SharedPreferences.
     *
     * @return ProfilePhoto with the persisted URI, or null URI if none saved
     */
    public ProfilePhoto load() {
        String s = prefs.getString(KEY_URI, null);
        return new ProfilePhoto(s == null ? null : Uri.parse(s));
    }
}
