package com.example.thecodelab.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thecodelab.R;
import com.example.thecodelab.model.GithubUser;
import com.example.thecodelab.presenter.GithubPresenter;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity implements SingleUserView {
    GithubUser user;
    String imageName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getIncomingIntent();

        if (savedInstanceState != null){
            user = savedInstanceState.getParcelable("user");
            userProfile(user);
        } else{
            loadData();
        }
    }


    private void loadData() {
        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.fetchSingleUser(imageName, this);
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("image_name")){
            imageName = getIntent().getStringExtra("image_name");
            setImage(imageName);
        }
    }

    private void setImage(String imageName){
        TextView name = findViewById(R.id.image_description);
        name.setText(imageName);
    }

    @Override
    public void userProfile(GithubUser githubUser) {
        user = githubUser;
        TextView bio = findViewById(R.id.bioValue);
        bio.setText(githubUser.getBio());
        ImageView image = findViewById(R.id.image);
        Picasso.get().load(githubUser.getAvatar()).into(image);

        TextView org = findViewById(R.id.orgValue);
        String company = githubUser.getOrganisation();
        if (company != null){
            org.setText(githubUser.getOrganisation());
        }
        else {
            org.setText(getResources().getString(R.string.none));
        }
        TextView follower = findViewById(R.id.follower);
        follower.setText(String.valueOf(githubUser.getFollowers()));
        TextView following = findViewById(R.id.following);
        following.setText(String.valueOf(githubUser.getFollowing()));
        TextView numberOfRepos = findViewById(R.id.numberOfRepos);
        numberOfRepos.setText(String.valueOf(githubUser.getPublicRepos()));

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

}
