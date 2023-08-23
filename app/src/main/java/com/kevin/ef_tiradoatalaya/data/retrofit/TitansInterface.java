package com.kevin.ef_tiradoatalaya.data.retrofit;

import com.kevin.ef_tiradoatalaya.data.model.Titan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TitansInterface {

    @GET("titans")
    Call<TitanResponse> getListTitan();
}
