package com.example.thecodelab.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GithubUserTest {
    @Test
    public void testGithubUsers(){
        String username = "NabunyaLilian";
        String organisation = "Andela";
        String avatar = "https://avatars3.githubusercontent.com/u/41575932?v=4";
        String bio = "Am passionate about writing code that solves real world problems and eases people's lives.";
        String githubUrl ="https://github.com/NabunyaLilian";
        int followers = 3;
        int following = 0;
        int publicRepos = 10;

        GithubUser githubUser = new GithubUser(username,organisation,avatar,bio,githubUrl,followers,following,publicRepos);

        assertEquals(username, githubUser.getUsername());
        assertEquals(organisation, githubUser.getOrganisation());
        assertEquals(avatar, githubUser.getAvatar());
        assertEquals(bio, githubUser.getBio());
        assertEquals(githubUrl, githubUser.getGithubUrl());
        assertEquals(followers, githubUser.getFollowers());
        assertEquals(following, githubUser.getFollowing());
        assertEquals(publicRepos, githubUser.getPublicRepos());

    }
}