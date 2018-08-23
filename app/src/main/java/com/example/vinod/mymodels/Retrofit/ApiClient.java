package com.example.vinod.mymodels.Retrofit;

import android.Manifest;
import android.support.annotation.RequiresPermission;

import java.io.Serializable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vinod on 8/9/2018.
 */

public class ApiClient implements Serializable {

   /* Retrofit Singleton
    https://gist.github.com/h4h13/d6fb27e81bfae6f2bf6d0c7a059a3f7b*/

    private static APIInterface apiInterface;
    private static volatile Retrofit retrofit = null;
    private static final String BASE_URL = " https://api.github.com/";

    private ApiClient(){
        if (retrofit !=null && apiInterface != null)
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
    }


    public static APIInterface getApiService() {
        return initRetrofitService();
    }

    private static APIInterface initRetrofitService() {
        if (apiInterface == null) {
            synchronized (ApiClient.class) {
                if (apiInterface == null) {
                    apiInterface = getRetrofit().create(APIInterface.class);
                }
            }
        }
        return apiInterface;
    }
    @RequiresPermission(Manifest.permission.INTERNET)
    private synchronized  static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (ApiClient.class){
                if (retrofit==null)
                    retrofit = new retrofit2.Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
            }

        }
        return retrofit;
    }



    protected Retrofit readResolve() {
        return getRetrofit();
    }
    protected APIInterface readResolveService() {
        return getApiService();
    }
}
