package com.example.dell.ejaaztest.Model;

/**
 * Created by DELL on 09/06/2017.
 */
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/v1_1/search ")
    Call<SearchResponse> getSearch(@Body SearchRequest request);
}
