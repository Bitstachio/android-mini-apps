package com.github.bitstachio.contactmanager.db;

import com.github.bitstachio.contactmanager.model.Contact;

import java.util.ArrayList;

import lombok.Getter;

public class MockDatabase {

    @Getter
    private static final ArrayList<Contact> contacts = new ArrayList<>();

    static {
        contacts.add(new Contact("Alice", "Johnson", "416-555-0182", "alice.johnson@email.com", "1998-04-12", "College friend"));
        contacts.add(new Contact("Brian", "Lopez", "647-555-0154", "brian.lopez@email.com", "1995-09-23", "Gym buddy"));
        contacts.add(new Contact("Catherine", "Nguyen", "905-555-0177", "cathy.nguyen@email.com", "2000-12-01", "Co-worker at TechCorp"));
        contacts.add(new Contact("David", "Kim", "437-555-0143", "david.kim@email.com", "1997-02-18", "Old neighbor"));
        contacts.add(new Contact("Ella", "Martinez", "289-555-0111", "ella.martinez@email.com", "1993-07-08", "Project teammate"));
        contacts.add(new Contact("Frank", "Williams", "613-555-0139", "frank.williams@email.com", "1989-10-30", "Family friend"));
        contacts.add(new Contact("Grace", "Patel", "519-555-0107", "grace.patel@email.com", "1999-06-21", "University classmate"));
        contacts.add(new Contact("Hassan", "Rahman", "416-555-0172", "hassan.rahman@email.com", "1996-11-05", "Work mentor"));
        contacts.add(new Contact("Isabella", "Chen", "647-555-0166", "isabella.chen@email.com", "1994-03-15", "Photography club"));
        contacts.add(new Contact("Jack", "Bennett", "905-555-0124", "jack.bennett@email.com", "1992-01-28", "Friend from soccer"));
        contacts.add(new Contact("Kaitlyn", "Singh", "289-555-0193", "kaitlyn.singh@email.com", "1997-05-09", "Roommate"));
        contacts.add(new Contact("Liam", "Anderson", "613-555-0189", "liam.anderson@email.com", "1991-08-17", "Former colleague"));
        contacts.add(new Contact("Maya", "Thompson", "437-555-0195", "maya.thompson@email.com", "1998-12-24", "Book club friend"));
        contacts.add(new Contact("Nathan", "Brown", "416-555-0119", "nathan.brown@email.com", "1995-04-03", "High school friend"));
        contacts.add(new Contact("Olivia", "Davis", "905-555-0188", "olivia.davis@email.com", "1999-09-10", "Cousin"));
    }
}
