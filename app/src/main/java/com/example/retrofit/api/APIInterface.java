package com.example.retrofit.api;


import com.example.retrofit.model.PostModel;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/posts")
    Call<List<PostModel>> getAllPosts();
}
