package com.github.bitstachio.contactmanager.persistence.sharedprefs.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.bitstachio.contactmanager.model.Contact;
import com.github.bitstachio.contactmanager.persistence.IContactDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedPrefsContactDao implements IContactDao {

    private static final String PREFS_NAME = "contacts_prefs";
    private static final String KEY_CONTACTS = "contacts_list";

    private final SharedPreferences prefs;
    private final Gson gson;

    public SharedPrefsContactDao(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // Helper: load all contacts from SharedPreferences
    private List<Contact> loadContacts() {
        String json = prefs.getString(KEY_CONTACTS, null);
        if (json == null || json.isEmpty()) return new ArrayList<>();

        Type type = new TypeToken<List<Contact>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    // Helper: save list of contacts to SharedPreferences
    private void saveContacts(List<Contact> contacts) {
        String json = gson.toJson(contacts);
        prefs.edit().putString(KEY_CONTACTS, json).apply();
    }

    @Override
    public List<Contact> getAll() {
        return loadContacts();
    }

    @Override
    public Contact getById(int id) {
        for (Contact contact : loadContacts()) {
            if (contact.getId() == id) return contact;
        }
        return null;
    }

    @Override
    public long insert(Contact contact) {
        List<Contact> contacts = loadContacts();

        // Generate a new ID
        int maxId = 0;
        for (Contact c : contacts) {
            if (c.getId() > maxId) maxId = c.getId();
        }
        int newId = maxId + 1;

        // Since your Contact has final fields, you need to create a new instance with the new ID
        Contact newContact = new Contact(
                newId,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getBirthday(),
                contact.getNotes()
        );

        contacts.add(newContact);
        saveContacts(contacts);
        return newId;
    }

    @Override
    public int update(Contact contact) {
        List<Contact> contacts = loadContacts();
        boolean updated = false;

        List<Contact> updatedList = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getId() == contact.getId()) {
                updatedList.add(contact);
                updated = true;
            } else {
                updatedList.add(c);
            }
        }

        if (updated) {
            saveContacts(updatedList);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int delete(Contact contact) {
        List<Contact> contacts = loadContacts();
        boolean removed = contacts.removeIf(c -> c.getId() == contact.getId());

        if (removed) {
            saveContacts(contacts);
            return 1;
        } else {
            return 0;
        }
    }
}
