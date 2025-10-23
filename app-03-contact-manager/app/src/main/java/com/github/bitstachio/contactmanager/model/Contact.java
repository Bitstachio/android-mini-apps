package com.github.bitstachio.contactmanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Data;

@Entity
@Data
// TODO: Implementing Serializable is a hacky solution
// Consider removing it and only pass contact ID between activities
public class Contact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;
    private final String birthday;
    private final String notes;
    private final boolean isSqlite;
}
