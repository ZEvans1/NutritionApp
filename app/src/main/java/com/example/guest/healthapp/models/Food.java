package com.example.guest.healthapp.models;

import org.parceler.Parcel;

@Parcel
public class Food {
    String name;
//    String photo;
    String tagId;

    public Food() {}

    public Food(String name, String tagId) {
        this.name = name;
//        this.photo = photo;
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

//    public String getPhoto() {
//        return photo;
//    }

    public String getTagId() {
        return tagId;
    }
}
