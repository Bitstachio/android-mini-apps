package com.github.bitstachio.contactmanager.persistence;

import com.github.bitstachio.contactmanager.model.Contact;

import java.util.ArrayList;

import lombok.Getter;

public class MockDatabase {

    @Getter
    private static final ArrayList<Contact> contacts = new ArrayList<>();

    public static Contact getEmpty() {
        return new Contact(
                0,
                "First Name",
                "Last Name",
                "(000) 000-0000",
                "email@example.com",
                "YYYY-MM-DD",
                "No notes",
                true
        );
    }
}
