package com.github.bitstachio.listdemo.activities;

public class Player {

    String name;
    String position;
    String description;

    int imageResId;
    int thumbnailResId;

    public Player(String name, int thumbnailResId, int imageResId, String position, String description) {
        this.name = name;
        this.thumbnailResId = thumbnailResId;
        this.imageResId = imageResId;
        this.position = position;
        this.description = description;
    }

    public int getImage() {
        return imageResId;
    }

    public String getTitle() {
        return name;
    }

    public String getDescription() {
        return position;
    }
}
