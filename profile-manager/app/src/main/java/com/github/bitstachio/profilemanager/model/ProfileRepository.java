package com.github.bitstachio.profilemanager.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class ProfileRepository {
    private static final String PREFS = "profile_repo";
    private static final String KEY_URI = "profile_uri";

    private final SharedPreferences prefs;

    public ProfileRepository(Context ctx) {
        prefs = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public void save(ProfilePhoto photo) {
        prefs.edit()
                .putString(KEY_URI, photo.getImageUri() == null ? null : photo.getImageUri().toString())
                .apply();
    }

    public ProfilePhoto load() {
        String s = prefs.getString(KEY_URI, null);
        return new ProfilePhoto(s == null ? null : Uri.parse(s));
    }
}
