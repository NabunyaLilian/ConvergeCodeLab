package com.example.thecodelab.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GithubUser implements Parcelable {
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

    public GithubUser(Parcel in) {
        username = in.readString();
        organisation = in.readString();
        avatar = in.readString();
        bio = in.readString();
        githubUrl = in.readString();
        followers = in.readInt();
        following = in.readInt();
        publicRepos = in.readInt();
    }

    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {
        @Override
        public GithubUser createFromParcel(Parcel in) {
            return new GithubUser(in);
        }

        @Override
        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(organisation);
        dest.writeString(avatar);
        dest.writeString(bio);
        dest.writeString(githubUrl);
        dest.writeInt(followers);
        dest.writeInt(following);
        dest.writeInt(publicRepos);
    }
}
