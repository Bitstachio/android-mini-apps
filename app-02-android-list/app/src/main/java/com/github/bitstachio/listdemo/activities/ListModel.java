package com.github.bitstachio.listdemo.activities;

public class ListModel {
    int imageResId;
    String title;
    String description;


    public ListModel(String title, String description, int listImage) {
        this.imageResId = listImage;
        this.title = title;
        this.description = description;


    }


    public int getImage() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
