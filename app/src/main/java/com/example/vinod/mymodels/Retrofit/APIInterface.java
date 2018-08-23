package com.example.vinod.mymodels.Retrofit;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Vinod on 8/9/2018.
 */

public interface APIInterface {

    @GET("/users/{user}/repos")
        //Here '{user}' will be dynamic
    Call<List<GitHubRepo>> reposForUser(@Path("user") String user);

    @POST("users/new")
    Call<User> createUser(@Body User user);

    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description,@Part MultipartBody.Part file);

    @GET
    public Call<ResponseBody> profilePicture(@Url String url);

    /*form-urlencoded requests to send data to a server or API. The data is sent within the request body and not as an url parameter.
    form-urlencoded: for POST request*/
    @FormUrlEncoded
    @POST("/tasks")
    Call<User> createTasks(@Field("name") String name,@Field("email") String email,
            @Field("age") String age,@Field("topic") String topic);  //For universal use 'ResponseBody' in call
    // we can use field like : @Field(value = "name", encoded = true) String name bydefault encoded=false

    @FormUrlEncoded
    @POST("/tasks")
    Call<User> createTasksMap(@FieldMap Map<String, String> fields);
    // we can use field like :@FieldMap(encoded = true) Map<String, String> fields

    @FormUrlEncoded
    @POST("/tasks")
    Call<User> createTasksMap2(@FieldMap Map<String, Object> fields,@Field("topics") List<String> topics);
    //if we send various type of data use object instead of string and define separately fields like @Field("topics") List<String> topics or @Field("id")int id

    @GET("/tasks")
    Call<ResponseBody> searchForUser(@Query("id")Integer id,@Query("order") String order);

    //OR  (we can use api-key in another way which is repeadedly called)

    @GET("/tasks")
    Call<ResponseBody> searchForUser1(@QueryMap Map<String, Object> queries);

}
