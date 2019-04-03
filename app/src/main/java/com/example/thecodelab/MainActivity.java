package com.example.thecodelab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.thecodelab.model.GithubUser;
import com.example.thecodelab.presenter.GithubPresenter;
import com.example.thecodelab.view.GithubUsersView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements GithubUsersView {
    List<GithubUser> githubUserList;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.fetchUsers(this);

    }

    private void initRecyclerView(List<GithubUser> githubUserList){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ItemListAdapter adapter = new ItemListAdapter(githubUserList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void readyUsers(List<GithubUser> githubUsers) {
        Log.d(TAG, "readyUsers: Fetch");
        githubUserList = githubUsers;
        initRecyclerView(githubUserList);
    }
}
