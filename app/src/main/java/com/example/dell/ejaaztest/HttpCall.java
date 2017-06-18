package com.example.dell.ejaaztest;

import android.app.Activity;
import android.util.Log;

import com.example.dell.ejaaztest.Model.ApiInterface;
import com.example.dell.ejaaztest.Model.Fields;
import com.example.dell.ejaaztest.Model.SearchRequest;
import com.example.dell.ejaaztest.Model.SearchResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 09/06/2017.
 */

public class HttpCall {
    private Activity activity;
    private ApiResponse apiResponse;

    public HttpCall(Activity activity, ApiResponse apiResponse) {

        this.activity = activity;
        this.apiResponse = apiResponse;
    }
    public void getData(SearchRequest searchRequest) {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<SearchResponse> connection = service.getSearch(searchRequest);

        connection.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                String reponseStr = response.toString().replaceAll("\\\\", "");
                Log.e("new String", reponseStr);
                apiResponse.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

                Log.e("QR ", " " + t.getLocalizedMessage());
                apiResponse.onFailed(t.getLocalizedMessage());
            }
        });

    }
}
