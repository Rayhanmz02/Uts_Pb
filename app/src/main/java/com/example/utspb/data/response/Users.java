package com.example.utspb.data.response;
import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("login")
    private String username;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String name;
    private String bio;
    private String email;
    @SerializedName("twitter_username")
    private String Twitter;

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
    }

    public String getTwitter() {
        return Twitter;
    }
}