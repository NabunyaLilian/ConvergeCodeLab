package com.example.thecodelab.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://api.github.com";

    public GithubApi getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit
                     .Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
        }
        return retrofit.create(GithubApi.class);
    }

}
