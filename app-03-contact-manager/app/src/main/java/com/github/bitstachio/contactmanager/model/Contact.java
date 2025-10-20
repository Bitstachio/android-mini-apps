package com.github.bitstachio.contactmanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity
@Data
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;
    private final String birthday;
    private final String notes;
}
