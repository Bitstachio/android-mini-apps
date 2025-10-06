package com.github.bitstachio.listdemo.activities;

public class ListModel {
    int imageResId;
    String name;
    String position;
    String description;


    public ListModel(String name, String position, String description, int listImage) {
        this.imageResId = listImage;
        this.name = name;
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
