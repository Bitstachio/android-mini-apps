package com.github.bitstachio.contactmanager.model;

import lombok.Data;

@Data
public class Contact {
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;
    private final String birthday;
    private final String notes;
}
