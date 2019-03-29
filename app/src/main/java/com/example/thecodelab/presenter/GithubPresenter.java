package com.example.thecodelab.presenter;


import com.example.thecodelab.model.GithubUser;
import com.example.thecodelab.model.GithubUsersResponse;
import com.example.thecodelab.service.GithubService;
import com.example.thecodelab.view.GithubUsersView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GithubPresenter {
    GithubService githubService;

    public GithubPresenter() {
        if(this.githubService == null){
            this.githubService = new GithubService();
        }
    }

    public void throwException(){
        try {
            throw new InterruptedException("Something went wrong!");
        } catch (InterruptedException e) { }
    }


    public void fetchUsers(final GithubUsersView githubUsersView){
        githubService
                .getRetrofitInstance()
                .getUsers()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                        GithubUsersResponse githubUsersResponse = response.body();

                        if (githubUsersResponse != null && githubUsersResponse.getUsers() !=null){
                            List<GithubUser> githubUser = githubUsersResponse.getUsers();
                            githubUsersView.readyUsers(githubUser);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUsersResponse> call, Throwable t) {

                        throwException();
                    }
                });
    }

}
