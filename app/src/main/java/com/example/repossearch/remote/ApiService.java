package com.example.repossearch.remote;

import com.example.repossearch.model.Data;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("users/{name}/repos")
    Call<ArrayList<Data>> getData(@Path("name") String name);


}
