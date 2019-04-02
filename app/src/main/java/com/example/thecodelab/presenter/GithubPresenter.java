package com.example.thecodelab.presenter;

import android.util.Log;

import com.example.thecodelab.ItemListAdapter;
import com.example.thecodelab.model.GithubUser;
import com.example.thecodelab.model.GithubUsersResponse;
import com.example.thecodelab.service.GithubService;
import com.example.thecodelab.view.GithubUsersView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {
    private GithubUsersView githubUsersView;
    private GithubService githubService;


    public GithubPresenter(GithubUsersView githubUsersView) {
        this.githubUsersView = githubUsersView;

        if(this.githubService == null){
            this.githubService = new GithubService();
        }
    }

    public void fetchUsers(){
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
                        Log.d("TAG", "onFailure: returned: " + t);

                    }
                });
    }
}
