package com.github.bitstachio.contactmanager.persistence;

import com.github.bitstachio.contactmanager.model.Contact;

import java.util.Collections;
import java.util.List;

public class SharedPrefsContactDao implements IContactDao {
    @Override
    public List<Contact> getAll() {
        return Collections.emptyList();
    }

    @Override
    public Contact getById(int id) {
        return null;
    }

    @Override
    public long insert(Contact contact) {
        return 0;
    }

    @Override
    public int update(Contact contact) {
        return 0;
    }

    @Override
    public int delete(Contact contact) {
        return 0;
    }
}
