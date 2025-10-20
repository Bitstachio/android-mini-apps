package com.github.bitstachio.contactmanager.persistence.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.bitstachio.contactmanager.persistence.IContactDao;
import com.github.bitstachio.contactmanager.model.Contact;

import java.util.List;

@Dao
public interface IRoomContactDao extends IContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Query("SELECT * FROM Contact WHERE id = :id")
    Contact getById(int id);

    @Insert
    long insert(Contact contact);

    @Update
    int update(Contact contact);

    @Delete
    int delete(Contact contact);
}
