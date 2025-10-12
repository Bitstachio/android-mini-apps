package com.github.bitstachio.contactmanager.persistence;

import com.github.bitstachio.contactmanager.model.Contact;

import java.util.List;

public interface IContactDao {
    List<Contact> getAll();
    Contact getById(int id);
    long insert(Contact contact);
    int update(Contact contact);
    int delete(Contact contact);
}
