package com.github.bitstachio.listdemo.activities;

public class Player {

    // =========================
    // Fields
    // =========================

    String name;
    String position;
    String description;

    int imageResId;
    int thumbnailResId;

    // =========================
    // Constructors
    // =========================

    public Player(String name, int thumbnailResId, int imageResId, String position, String description) {
        this.name = name;
        this.thumbnailResId = thumbnailResId;
        this.imageResId = imageResId;
        this.position = position;
        this.description = description;
    }

    // =========================
    // Getters
    // =========================

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
