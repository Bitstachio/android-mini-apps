package com.github.bitstachio.contactmanager.persistence.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.github.bitstachio.contactmanager.persistence.room.dao.IRoomContactDao;
import com.github.bitstachio.contactmanager.model.Contact;

@Database(
        entities = {Contact.class},
        version = 1,
        exportSchema = false
)
public abstract class ExtendedRoomDatabase extends RoomDatabase {
    public abstract IRoomContactDao contactDao();
}
