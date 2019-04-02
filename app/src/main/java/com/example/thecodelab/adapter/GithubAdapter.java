package com.example.thecodelab.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thecodelab.view.DetailActivity;
import com.example.thecodelab.R;
import com.example.thecodelab.model.GithubUser;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.ViewHolder>{
    List<GithubUser> githubUserArray;

    public GithubAdapter(List<GithubUser> githubUserArray) {
        this.githubUserArray = githubUserArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem) ;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final GithubUser githubUser = githubUserArray.get(holder.getAdapterPosition());

        Picasso.get().load(githubUser.getAvatar()).into(holder.imageView);
        holder.textView.setText(githubUser.getUsername());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),githubUser.getUsername(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("image_name", githubUser.getUsername());
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return githubUserArray.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (CircleImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
