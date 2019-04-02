package com.example.thecodelab.service;

import com.example.thecodelab.model.GithubUser;
import com.example.thecodelab.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubApi {

    @GET("search/users?q=type:User+location:Nairobi+language:JAVA")
    Call<GithubUsersResponse> getUsers();

    @GET("users/{username}")
    Call<GithubUser> getUser();
}
