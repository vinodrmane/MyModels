package com.example.vinod.mymodels.WebDemoProject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Vinod on 9/14/2018.
 */

public interface GitInterface {
    @GET("/users/{user}/repos")
    Call<List<DemoGitHubRepo>> reposForUserDemo(@Path("user") String user);
}
