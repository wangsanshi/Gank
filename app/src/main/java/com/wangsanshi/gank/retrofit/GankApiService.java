package com.wangsanshi.gank.retrofit;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GankApiService {
    String GANK_API_URL = "http://gank.io/";

    /*
     * @param type,请求数据的类型
     * @param count，请求数据的数量
     * @param page，请求数据的页数
     * @return JsonObject，返回JsonObject对象
     */
    @GET("api/data/{type}/{count}/{page}")
    Call<JsonObject> getResponse(@Path("type") String type
            , @Path("count") int count
            , @Path("page") int page);

    /*
     * 下载图片
     * @param imageUrl，下载图片的url
     * @return 返回响应体
     */
    @GET
    Call<ResponseBody> downLoadImage(@Url String imageUrl);
}
