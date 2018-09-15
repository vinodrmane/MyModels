package com.example.vinod.mymodels.WebDemoProject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vinod.mymodels.Database;
import com.example.vinod.mymodels.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadActivity extends AppCompatActivity {

    ListView loadView;
    ArrayList<DemoGitHubRepo> loadUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        loadView = (ListView) findViewById(R.id.loadView);
        loadData();
    }


    private void loadData() {

        Call<List<DemoGitHubRepo>> call = GitClient.getApiService().reposForUserDemo("fs-opensource");
        call.enqueue(new Callback<List<DemoGitHubRepo>>() {
            @Override
            public void onResponse(Call<List<DemoGitHubRepo>> call, Response<List<DemoGitHubRepo>> response) {
               List<DemoGitHubRepo> repos = response.body();
               MyApplication.getWritableDatabase().insertProducts(repos);
            }

            @Override
            public void onFailure(Call<List<DemoGitHubRepo>> call, Throwable t) {
                Toast.makeText(LoadActivity.this, "error :(", Toast.LENGTH_SHORT).show();

            }
        });


        loadUser=MyApplication.getWritableDatabase().readProducts();
        loadView.setAdapter(new DemoGitHubRepoAdapter(this,loadUser));
    }
}
