package com.example.thecodelab.view;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.thecodelab.R;
import com.example.thecodelab.adapter.GithubAdapter;
import com.example.thecodelab.model.GithubUser;
import com.example.thecodelab.presenter.GithubPresenter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements GithubUsersView {
    List< GithubUser> githubUserList;
    Parcelable state;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            readyUsers(savedInstanceState.<GithubUser>getParcelableArrayList("users"));
        }else{
            GithubPresenter githubPresenter = new GithubPresenter();
            githubPresenter.fetchUsers(this);
        }
    }

    private void initRecyclerView(List<GithubUser> githubUserList){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GithubAdapter adapter = new GithubAdapter(githubUserList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void readyUsers(List<GithubUser> githubUsers) {
        githubUserList = githubUsers;
        initRecyclerView(githubUserList);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("users", (ArrayList<? extends Parcelable>) githubUserList);
        outState.putParcelable("state", state);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        githubUserList = savedInstanceState.getParcelableArrayList("users");
        state = savedInstanceState.getParcelable("state");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (state != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(state);
            initRecyclerView(githubUserList);
        }
    }
}
