package com.example.thecodelab.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.thecodelab.adapter.ItemListAdapter;
import com.example.thecodelab.R;

import java.util.List;



public class MainActivity extends AppCompatActivity {
    List<String> mUsername;
    List<String> mImageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageBitmaps();
    }

    private void initImageBitmaps(){
        mImageUrls.add("https://opinionhall.com/wp-content/uploads/2018/07/weird-bug-makes-samsung-phones-text-random-photos-to-random-people.jpg");
        mUsername.add("Joel");
        mImageUrls.add("https://pbs.twimg.com/profile_images/586332868886061056/9T-JNx1o_400x400.jpg");
        mUsername.add("Natalia");
        mImageUrls.add("https://pbs.twimg.com/profile_images/645308753420533760/LVuQyAtK.jpg");
        mUsername.add("George");
        mImageUrls.add("https://www.sololearn.com/Icons/Avatars/827743.jpg");
        mUsername.add("Ragit");
        mImageUrls.add("http://chrisbusse.com/images/busse-256px.jpg");
        mUsername.add("Bruse");
        mImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRaOJEAXM2Buo5zbtpLNnx35Whojg047RaVN9Wqh_cmoJaoqVoL");
        mUsername.add("Meghane");
        mImageUrls.add("https://lh6.googleusercontent.com/-CiKRizFmokk/AAAAAAAAAAI/AAAAAAAAbng/f8NkvgI4apw/photo.jpg");
        mUsername.add("Lucas");
        mImageUrls.add("https://css-weekly.com/wp-content/uploads/2015/01/martin-lenngren.jpg");
        mUsername.add("Josh");
        mImageUrls.add("https://www.rasmussen.edu/-/media/images/blog/authors/callie-malvik.jpg?h=256&w=256&la=en&hash=CAFC6D4BEC2F976BB6E02529C37C74652BFCA305");
        mUsername.add("Helen");
        mImageUrls.add("https://secure.gravatar.com/avatar/ab29f2af3d2ec0c24910490046d8736b?s=256&d=mm&r=g");
        mUsername.add("Heidi");
        mImageUrls.add("https://i.pinimg.com/474x/aa/70/78/aa7078671217cd09c09ee7add6e37376--handsome-boys-china.jpg");
        mUsername.add("Chan");
        mImageUrls.add("https://secure.gravatar.com/avatar/0cc9ac95914b9f88a81ab565d9c7925c?s=128&d=identicon&r=g");
        mUsername.add("Carol");
        mImageUrls.add("http://bhangrateamsforum.com/a/data/avatars/m/1/1144.jpg?1510629640");
        mUsername.add("Raj");
        mImageUrls.add("https://secure.gravatar.com/avatar/df7553a5f85677c904a27d102b09183d?s=256&d=mm&r=g");
        mUsername.add("Nick");
        mImageUrls.add("https://iapp.org/media/headshots/0011a00000DlO9KAAV.png");
        mUsername.add("Greg");
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ItemListAdapter adapter = new ItemListAdapter(mUsername, mImageUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
