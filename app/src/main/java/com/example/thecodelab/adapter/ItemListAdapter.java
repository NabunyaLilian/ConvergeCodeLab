package com.example.thecodelab.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thecodelab.R;
import com.example.thecodelab.view.DetailActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder>{
    List<String> mTextView;
    List<String> mImageView;
    Context mContext;

    public ItemListAdapter(List<String> mTextView, List<String> mImageView, Context mContext) {
        this.mTextView = mTextView;
        this.mImageView = mImageView;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem) ;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImageView.get(position))
                .into(holder.imageView);

        holder.textView.setText(mTextView.get(holder.getAdapterPosition()));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mTextView.get(holder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("image_url", mImageView.get(holder.getAdapterPosition()));
                intent.putExtra("image_name", mTextView.get(holder.getAdapterPosition()));
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mTextView.size();
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
