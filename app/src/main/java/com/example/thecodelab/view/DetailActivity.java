package com.example.thecodelab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;


import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thecodelab.R;
import com.example.thecodelab.model.GithubUser;
import com.example.thecodelab.presenter.GithubPresenter;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity implements SingleUserView {
    GithubUser user;
    String imageName;
    String userName, profileURL;
    Intent sharingIntent;
    private ShareActionProvider shareActionProvider;
    CountingIdlingResource mcountingIdlingResource = new CountingIdlingResource("DETAIL_LOADER");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getIncomingIntent();
        startSharingIntent("lillian", "profile url");
        if (savedInstanceState != null) {
            user = savedInstanceState.getParcelable("user");
            userProfile(user);
        } else {
            loadData();
        }

    }

    private void startSharingIntent(String username, String profileURL) {
        sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Check out this " +
                "awesome developer @" + username + ", " + profileURL + " .");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        shareActionProvider.setShareIntent(sharingIntent);
        return true;
    }

    private void loadData() {
        mcountingIdlingResource.increment();
        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.fetchSingleUser(imageName, this);
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("image_name")) {
            imageName = getIntent().getStringExtra("image_name");
            setImage(imageName);

        }
    }

    private void setShareIntent(Intent shareIntent) {
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    private void setImage(String imageName) {
        TextView name = findViewById(R.id.image_description);
        name.setText(imageName);
    }


    @Override
    public void userProfile(GithubUser githubUser) {
        user = githubUser;
        ImageView image = findViewById(R.id.image);

        if (githubUser != null) {
            profileURL = githubUser.getGithubUrl();
            TextView bio = findViewById(R.id.bioValue);
            bio.setText(githubUser.getBio());

            Picasso.get().load(githubUser.getAvatar()).into(image);
            TextView org = findViewById(R.id.orgValue);
            String company = githubUser.getOrganisation();
            if (company != null) {
                org.setText(githubUser.getOrganisation());
            } else {
                org.setText(getResources().getString(R.string.none));
            }
            TextView follower = findViewById(R.id.follower);
            follower.setText(String.valueOf(githubUser.getFollowers()));
            TextView following = findViewById(R.id.following);
            following.setText(String.valueOf(githubUser.getFollowing()));
            TextView numberOfRepos = findViewById(R.id.numberOfRepos);
            numberOfRepos.setText(String.valueOf(githubUser.getPublicRepos()));

        } else {
            Picasso.get().load("https://avatars1.githubusercontent.com/u/8445?v=4").into(image);
            profileURL = getString(R.string.notAssigned);
            TextView bio = findViewById(R.id.bioValue);
            bio.setText(getString(R.string.notAssigned));

            TextView org = findViewById(R.id.orgValue);
            org.setText(getString(R.string.notAssigned));
            TextView follower = findViewById(R.id.follower);
            follower.setText(getString(R.string.notAssigned));
            TextView following = findViewById(R.id.following);
            following.setText(getString(R.string.notAssigned));
            TextView numberOfRepos = findViewById(R.id.numberOfRepos);
            numberOfRepos.setText(getString(R.string.notAssigned));

        }
        startSharingIntent(imageName, profileURL);
        setShareIntent(sharingIntent);
        mcountingIdlingResource.decrement();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("user", user);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        user = savedInstanceState.getParcelable("user");
    }

    @VisibleForTesting
    public CountingIdlingResource getIdlingResourceInTest() {
        return mcountingIdlingResource;
    }
}
