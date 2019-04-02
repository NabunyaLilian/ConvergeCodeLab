package com.example.thecodelab.view;

import com.example.thecodelab.model.GithubUser;

import java.util.List;

public interface GithubUsersView {
    void readyUsers(List<GithubUser> githubUsers);
}
