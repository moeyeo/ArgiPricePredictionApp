package com.moeyeo.argipredictionapp.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface vegPriceGenerate {
    @GET("vegsDetail/{id}")
    Call<vegDetails> getVeg(@Path("id") String vegId);

    @GET("vegsDetail")
    Call<VegList> getVegList();

}
