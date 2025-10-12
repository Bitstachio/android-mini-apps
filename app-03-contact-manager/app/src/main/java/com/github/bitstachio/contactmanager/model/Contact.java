package com.github.bitstachio.contactmanager.model;

import lombok.Data;

@Data
public class Contact {
    public String firstName;
    public String lastName;
    public String phone;
    public String email;
    public String birthday;
    public String notes;
}
