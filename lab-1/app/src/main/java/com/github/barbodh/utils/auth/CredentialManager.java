package com.github.barbodh.utils.auth;

import java.util.HashMap;

public class CredentialManager {

    private static final HashMap<String, String> credentials = new HashMap<>();

    // Hardcoded credentials
    static {
        add("barbod", "1234");
        add("mehrshad", "2345");
        add("amir", "3456");
    }

    public static void add(String username, String password) {
        credentials.put(username, password);
    }

    public static boolean validate(String username, String password) {
        if (credentials.containsKey(username)) {
            String storedPassword = credentials.get(username);
            return storedPassword != null && storedPassword.equals(password);
        }
        return false;
    }
}
