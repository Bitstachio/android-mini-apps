package com.github.barbodh.utils.auth;

import java.util.HashMap;

/**
 * A simple in-memory credential manager used for authentication.
 * <p>
 * This class stores username-password pairs in a static {@link HashMap}.
 * It provides methods for adding users, checking for duplicate usernames,
 * and validating login credentials.
 * <p>
 * Note: This implementation is only intended for testing/demo purposes.
 * Passwords are stored in plain text and should never be used in production.
 */
public class CredentialManager {

    /**
     * Stores all username-password pairs in memory.
     */
    private static final HashMap<String, String> credentials = new HashMap<>();

    // =========================
    // Static Initialization
    // =========================

    // Initializes the credential store with hardcoded sample users
    static {
        add("barbod", "1234");
        add("mehrshad", "2345");
        add("amir", "3456");
    }

    // =========================
    // Simplified Public API
    // =========================

    /**
     * Adds a new username-password pair to the credential store.
     *
     * @param username the username to add
     * @param password the password associated with the username
     */
    public static void add(String username, String password) {
        credentials.put(username, password);
    }

    /**
     * Checks whether the given username already exists in the credential store.
     *
     * @param username the username to check
     * @return {@code true} if the username is already taken, {@code false} otherwise
     */
    public static boolean isDuplicateUsername(String username) {
        return credentials.containsKey(username);
    }

    /**
     * Validates a set of login credentials.
     * <p>
     * This checks if the provided username exists in the credential store
     * and whether the password matches the stored password.
     *
     * @param username the username to validate
     * @param password the password to validate
     * @return {@code true} if the credentials are valid, {@code false} otherwise
     */
    public static boolean validate(String username, String password) {
        if (credentials.containsKey(username)) {
            String storedPassword = credentials.get(username);
            return storedPassword != null && storedPassword.equals(password);
        }
        return false;
    }
}
