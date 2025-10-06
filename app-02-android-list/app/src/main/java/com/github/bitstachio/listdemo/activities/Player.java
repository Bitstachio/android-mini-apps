package com.github.bitstachio.listdemo.activities;

public class Player {

    // =========================
    // Fields
    // =========================

    int id;
    String name;
    String position;
    String description;

    int imageResId;
    int thumbnailResId;

    // =========================
    // Constructors
    // =========================

    public Player(int id, String name, int thumbnailResId, int imageResId, String position, String description) {
        this.id = id;
        this.name = name;
        this.thumbnailResId = thumbnailResId;
        this.imageResId = imageResId;
        this.position = position;
        this.description = description;
    }

    // =========================
    // Getters
    // =========================

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getThumbnailResId() {
        return thumbnailResId;
    }
}
