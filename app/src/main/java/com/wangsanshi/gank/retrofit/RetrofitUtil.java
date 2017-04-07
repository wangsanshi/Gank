package com.wangsanshi.gank.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = okhttpBuilder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(GankApiService.GANK_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}
