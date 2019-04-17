package com.example.thecodelab.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GithubUsersResponse {
    @SerializedName("items")
    List<GithubUser> users;

    public GithubUsersResponse() {
        this.users = new ArrayList<>();
    }

    public List<GithubUser> getUsers() {
        return users;
    }
}
