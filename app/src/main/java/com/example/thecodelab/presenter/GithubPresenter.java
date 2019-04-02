package com.example.thecodelab.presenter;

import com.example.thecodelab.ItemListAdapter;
import com.example.thecodelab.service.GithubService;

public class GithubPresenter {
    private ItemListAdapter itemListAdapter;
    private GithubService githubService;


    public GithubPresenter(ItemListAdapter itemListAdapter) {
        this.itemListAdapter = itemListAdapter;

        if(this.githubService == null){
            this.githubService = new GithubService();
        }
    }


}
