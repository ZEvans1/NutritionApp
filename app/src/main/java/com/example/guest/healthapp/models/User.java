package com.example.guest.healthapp.models;

import org.parceler.Parcel;

@Parcel
public class User {
    String userName;
    String uid;
    String photo;
//    String pushId;

//    public String getPushId() {
//        return pushId;
//    }
//
//    public void setPushId(String pushId) {
//        this.pushId = pushId;
//    }

    public User() {}

    public User(String userName, String uid, String photo) {
        this.userName = userName;
        this.photo = photo;
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
