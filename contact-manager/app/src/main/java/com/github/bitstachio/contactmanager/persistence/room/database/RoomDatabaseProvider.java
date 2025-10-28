package com.github.bitstachio.contactmanager.persistence.room.database;

import android.content.Context;

import androidx.room.Room;

import lombok.Getter;

@Getter
public class RoomDatabaseProvider {
    private static RoomDatabaseProvider instance;
    private final ExtendedRoomDatabase database;

    private RoomDatabaseProvider(Context context) {
        database = Room.databaseBuilder(
                context.getApplicationContext(),
                ExtendedRoomDatabase.class,
                "contacts.db"
        ).build();
    }

    public static synchronized RoomDatabaseProvider getInstance(Context context) {
        if (instance == null) {
            instance = new RoomDatabaseProvider(context);
        }
        return instance;
    }
}
