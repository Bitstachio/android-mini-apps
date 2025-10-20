package com.github.bitstachio.contactmanager.persistence;

import com.github.bitstachio.contactmanager.model.Contact;

import java.util.ArrayList;

import lombok.Getter;

public class MockDatabase {

    @Getter
    private static final ArrayList<Contact> contacts = new ArrayList<>();
}
