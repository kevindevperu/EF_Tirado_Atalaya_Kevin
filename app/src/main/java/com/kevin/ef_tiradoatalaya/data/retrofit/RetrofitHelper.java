package com.kevin.ef_tiradoatalaya.data.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static Retrofit instance;
    public static TitansInterface service;

    public  static Retrofit getInstance(){
        if (instance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.attackontitanapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getLoggingBuilder().build())
                    .build();
            instance = retrofit;
        }
        return instance;
    }

    public static OkHttpClient.Builder getLoggingBuilder(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        return builder;
    }
}
