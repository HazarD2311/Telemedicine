package com.example.hazard.telemedicine.logic;

/*
Синглтон для хранения профиля
*/

public class ProfileSingleton {

    private static ProfileSingleton instance;
    private String username;
    private String photoUrl;

    private ProfileSingleton() {
    }

    public static ProfileSingleton getInstance() {
        if (instance == null) {
            instance = new ProfileSingleton();
        }
        return instance;
    }

    public String getUsername() {
        return instance.username;
    }

    public void setUsername(String username) {
        instance.username = username;
    }

    public String getPhotoUrl() {
        return instance.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        instance.photoUrl = photoUrl;
    }
}
