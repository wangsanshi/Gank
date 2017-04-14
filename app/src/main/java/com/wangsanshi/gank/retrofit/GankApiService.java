package com.wangsanshi.gank.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApiService {
    String GANK_API_URL = "http://gank.io/";

    @GET("api/data/{type}/{count}/{page}")
    Call<Object> getResponse(@Path("type") String type, @Path("count") int count, @Path("page") int page);
}
