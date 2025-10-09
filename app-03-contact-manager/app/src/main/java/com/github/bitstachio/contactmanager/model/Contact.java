package com.github.bitstachio.contactmanager.model;

import lombok.Data;

@Data
public class Contact {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String birthday;
    private String notes;
}
