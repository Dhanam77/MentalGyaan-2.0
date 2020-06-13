package com.example.mentalgyaan.API;

import com.example.mentalgyaan.Model.Sentiment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("/sentiment")
    Call<Sentiment> getSentiment(@Body Sentiment sentiment);
}
