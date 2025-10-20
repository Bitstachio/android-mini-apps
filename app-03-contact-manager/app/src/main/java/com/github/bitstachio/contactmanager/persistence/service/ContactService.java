package com.github.bitstachio.contactmanager.persistence.service;

import android.content.Context;
import android.util.Log;

import com.github.bitstachio.contactmanager.model.Contact;
import com.github.bitstachio.contactmanager.persistence.IContactDao;
import com.github.bitstachio.contactmanager.persistence.PersistenceStrategy;
import com.github.bitstachio.contactmanager.persistence.room.dao.IRoomContactDao;
import com.github.bitstachio.contactmanager.persistence.room.database.RoomDatabaseProvider;

import java.util.List;

public class ContactService {
    private final IRoomContactDao roomContactDao;

    public ContactService(Context context) {
        this.roomContactDao = RoomDatabaseProvider.getInstance(context).getDatabase().contactDao();
    }

    public IContactDao dispatchDao(PersistenceStrategy strategy) {
        if (strategy == PersistenceStrategy.ROOM) return roomContactDao;
        // TODO
        // else if (strategy == PersistenceStrategy.SHARED_PREFS) throw new RuntimeException();
        else throw new RuntimeException();
    }

    public List<Contact> getAll(PersistenceStrategy strategy) {
        return dispatchDao(strategy).getAll();
    }

    public Contact getById(PersistenceStrategy strategy, int id) {
        return dispatchDao(strategy).getById(id);
    }

    public long insert(PersistenceStrategy strategy, Contact contact) {
        Log.d("ContactService", "Inserting contact via Room...");
        return dispatchDao(strategy).insert(contact);
    }

    public int update(PersistenceStrategy strategy, Contact contact) {
        return dispatchDao(strategy).update(contact);
    }

    public int delete(PersistenceStrategy strategy, Contact contact) {
        return dispatchDao(strategy).delete(contact);
    }
}
