package com.example.thecodelab.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUsersResponse {
    @SerializedName("items")
    private List<GithubUser> users;

    public GithubUsersResponse(List<GithubUser> users) {
        this.users = users;
    }

    public List<GithubUser> getUsers() {
        return users;
    }
}
