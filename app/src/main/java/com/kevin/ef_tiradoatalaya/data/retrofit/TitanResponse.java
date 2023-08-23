package com.kevin.ef_tiradoatalaya.data.retrofit;

import com.google.gson.annotations.SerializedName;
import com.kevin.ef_tiradoatalaya.data.model.Titan;

import java.util.List;

public class TitanResponse {

    @SerializedName("results")
    private List<Titan> titanList;

    public List<Titan> getResults() {
        return titanList;
    }

    public void setTitanList(List<Titan> titanList) {
        this.titanList = titanList;
    }
}
