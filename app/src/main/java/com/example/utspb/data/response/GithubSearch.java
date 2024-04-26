package com.example.utspb.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubSearch {
    @SerializedName("items")
    private List<Users> users;

    public List<Users> getUsers() {
        return users;
    }
}
