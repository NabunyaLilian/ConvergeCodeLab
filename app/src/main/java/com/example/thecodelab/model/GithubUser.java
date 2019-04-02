package com.example.thecodelab.model;

import com.google.gson.annotations.SerializedName;

public class GithubUser {
    @SerializedName("login")
    String username;

    @SerializedName("company")
    String organisation;

    @SerializedName("avatar_url")
    String avatar;

    @SerializedName("bio")
    String bio;

    @SerializedName("url")
    String githubUrl;

    @SerializedName("followers")
    int followers;

    @SerializedName("following")
    int following;

    @SerializedName("public_repos")
    int publicRepos;

    public GithubUser(String username, String organisation, String avatar,
                      String bio, String githubUrl, int followers,
                      int following, int publicRepos) {
        this.username = username;
        this.organisation = organisation;
        this.avatar = avatar;
        this.bio = bio;
        this.githubUrl = githubUrl;
        this.followers = followers;
        this.following = following;
        this.publicRepos = publicRepos;
    }

    public String getUsername() {
        return username;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getBio() {
        return bio;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public int getPublicRepos() {
        return publicRepos;
    }
}
