package com.example.thecodelab.view;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Parcelable;

import android.support.design.widget.Snackbar;

import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.thecodelab.R;
import com.example.thecodelab.adapter.GithubAdapter;
import com.example.thecodelab.model.GithubUser;
import com.example.thecodelab.presenter.GithubPresenter;
import com.example.thecodelab.util.NetworkListener;
import com.example.thecodelab.util.NetworkUtility;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements GithubUsersView, NetworkListener {
    List< GithubUser> githubUserList;
    Parcelable state;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDialog;
    static final String TAG = "MainActivity";
    final NetworkUtility networkUtility = new NetworkUtility(this);
    IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private Snackbar snackbar;
    CountingIdlingResource countingIdlingResource = new CountingIdlingResource("DATA_LOADER");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.registerReceiver(networkUtility, filter);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeToRefresh();
        recyclerView = findViewById(R.id.recyclerView);
        int orientation = this.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            gridSize(2);
        }else{
            gridSize(3);
        }
        if(savedInstanceState != null){
            readyUsers(savedInstanceState.<GithubUser>getParcelableArrayList("users"));
        }else{
            loadData();
        }
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("Github Users"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    Log.d(TAG, "run: something went wrong");

                }
                progressDialog.dismiss();
            }
        }).start();
    }

    private void initRecyclerView(List<GithubUser> githubUserList){
        GithubAdapter adapter = new GithubAdapter(githubUserList);
        recyclerView.setAdapter(adapter);
        countingIdlingResource.decrement();
    }

    public void gridSize(int size){
        recyclerView.setLayoutManager(new GridLayoutManager(this, size, GridLayoutManager.VERTICAL, false));
    }
    public void loadData(){
        countingIdlingResource.increment();
        showProgressDialog();
        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.fetchUsers(this);
    }

    private void swipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
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

    @Override
    public void withInternet() {
        if (snackbar != null) {
            Snackbar.make(findViewById(R.id.indeterminateBar),
                    "Connected to the internet", 3000).show();
            swipeToRefresh();
        }
    }

    @Override
    public void withNoInternet() {
        snackbar = Snackbar.make(findViewById(R.id.indeterminateBar),
                "No internet connection", Snackbar.LENGTH_INDEFINITE);
        snackbar.show();

    }

    @VisibleForTesting
    public CountingIdlingResource getIdlingResourceInTest() {
        return countingIdlingResource ;
    }
}
