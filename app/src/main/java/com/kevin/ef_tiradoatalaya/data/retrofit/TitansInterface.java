package com.kevin.ef_tiradoatalaya.data.retrofit;

import com.kevin.ef_tiradoatalaya.data.model.Titan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TitansInterface {

    @GET("titans")
    Call<TitanResponse> getListTitan();

    @GET("titans/{id}")
    Call<Titan> getTitanById(@Path("id") int id);
}
