package com.example.vinod.mymodels;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vinod.mymodels.Retrofit.ApiClient;
import com.example.vinod.mymodels.Retrofit.GitHubRepo;
import com.example.vinod.mymodels.Retrofit.GitHubRepoAdapter;
import com.example.vinod.mymodels.Retrofit.User;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class activity_second extends AppCompatActivity {

    @BindView(R.id.btn_logout)
    Button btnLogout;
    FirebaseAuth mAuth;
    private ListView listView;
    Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        listView = (ListView) findViewById(R.id.pagination_list);
        mAuth=FirebaseAuth.getInstance();
        callAllRetroServices();
        sendPictureWithDesc(fileUri);
        requestDynamicUrls();
        requestUrlEncode();
        requestPlainText();
        getQuery();

    }

    private void getQuery() {
// https://futurestud.io/tutorials/retrofit-2-how-to-add-query-parameters-to-every-request

        final String api_key="secret_key";
        OkHttpClient.Builder httpClient =new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", api_key)
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        // we have to add in retrofit instance {.client(httpClient)}
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",4);
        map.put("order","desc");
        Call<ResponseBody> call= ApiClient.getApiService().searchForUser1(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Toast.makeText(activity_second.this,"success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(activity_second.this,"error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestPlainText() {
      //  https://futurestud.io/tutorials/retrofit-2-how-to-send-plain-text-request-body
    }

    private void requestUrlEncode() {
/*        https://futurestud.io/tutorials/retrofit-send-data-form-urlencoded
        https://futurestud.io/tutorials/retrofit-send-data-form-urlencoded-using-fieldmap*/

        // For createTasks
        Call<User> callUser2= ApiClient.getApiService().createTasks("jon","jon@gmail.com","23","design");
        callUser2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                Toast.makeText(activity_second.this,"success",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(activity_second.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        // For createTasksMap
        Map<String,String> map=new HashMap<String,String>();
        map.put("name","jon");
        map.put("email","jon@gmail.com");
        map.put("age","23");
        map.put("topic","design");
        Call<User> callUser= ApiClient.getApiService().createTasksMap(map);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                Toast.makeText(activity_second.this,"success",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(activity_second.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        // For createTasksMap2
        Map<String,Object> map1=new HashMap<String,Object>();
        map.put("name","jon");
        map.put("email","jon@gmail.com");
        map.put("age","23");
        Call<User> callUser1= ApiClient.getApiService().createTasksMap2(map1, Arrays.asList(new String[]{"design","develop"}));
        callUser1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                Toast.makeText(activity_second.this,"success",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(activity_second.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void requestDynamicUrls() {

        //https://futurestud.io/tutorials/retrofit-2-how-to-use-dynamic-urls-for-requests
        String url="https://your.api.url/profile-picture/path";
        Call<ResponseBody> call = ApiClient.getApiService().profilePicture(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(activity_second.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendPictureWithDesc(Uri fileUri) {
    //https://futurestud.io/tutorials/retrofit-2-how-to-upload-files-to-server

// https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
       // File file = FileUtils.getFile(this, fileUri);

        File file = new File("/sdCard/picture/cat.png");//for example
        String descriptionString = "hello, this is description speaking";
        RequestBody description =RequestBody.create(MultipartBody.FORM, descriptionString);//MediaType.parse("text/plain"),
        RequestBody requestFile =RequestBody.create(MediaType.parse("image/*"),file);//For Any file type use : MediaType.parse(getContentResolver().getType(fileUri))
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        // finally, execute the request
        Call<ResponseBody> call = ApiClient.getApiService().upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,retrofit2.Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

    private void callAllRetroServices() {

        // dynamically send user for getting list of user
        Call<List<GitHubRepo>> call = ApiClient.getApiService().reposForUser("fs-opensource");
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, retrofit2.Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();
                listView.setAdapter(new GitHubRepoAdapter(activity_second.this, repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(activity_second.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });

        // send user details to server
        User user=new User("jon","jon@gmail.com",34,new String[]{"design","develop"});
        Call<User> callUser= ApiClient.getApiService().createUser(user);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                Toast.makeText(activity_second.this,response.body().getId(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(activity_second.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_logout)
    public void onViewClicked() {
        mAuth.signOut();
        LoginManager.getInstance().logOut();
    }
}
