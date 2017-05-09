package com.wangsanshi.gank.util;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wangsanshi.gank.entity.CollectionBean;
import com.wangsanshi.gank.entity.Constant;
import com.wangsanshi.gank.entity.GeneralBean;
import com.wangsanshi.gank.retrofit.GankApiService;
import com.wangsanshi.gank.retrofit.RetrofitUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utility {
    private static final String TAG = "Utility";

    /*
     * 计算发布时间距当前时间有多久
     * @param 发布时间
     * @return 发布时间距当前时间有多久
     */
    public static String timeFormat(@NonNull String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS", Locale.CHINA);
        Calendar calendar = Calendar.getInstance();

        Date publishDate = null;
        try {
            //发布时间格式为2017-05-08T11:22:01.540Z，非标准日期格式，需要将其中的T进行替换
            publishDate = sdf.parse(date.replace("T", "-"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = new Date();

        calendar.setTime(publishDate);
        int publishYear = calendar.get(Calendar.YEAR);
        int publishMonth = calendar.get(Calendar.MONTH) + 1;
        int publishDay = calendar.get(Calendar.DAY_OF_MONTH);
        int publishHour = calendar.get(Calendar.HOUR_OF_DAY);
        int publishMinute = calendar.get(Calendar.MINUTE);

        calendar.setTime(currentDate);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        if (publishYear != currentYear) {
            return (currentYear - publishYear) + "年前";
        }

        if (publishMonth != currentMonth) {
            return (currentMonth - publishMonth) + "个月前";
        }

        if (publishDay != currentDay) {
            return (currentDay - publishDay) + "天前";
        }

        if (publishHour != currentHour) {
            return (currentHour - publishHour) + "小时前";
        }

        if (publishMinute != currentMinute) {
            return (currentMinute - publishMinute) + "分钟前";
        }

        return "刚刚";
    }

    /*
     * 得到当前的日期
     * @return 当前的日期
     */
    private static String getCurrentDate() {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-M-d", Locale.CHINA);
        return spf.format(new Date());
    }

    /*
     * 保存收藏的信息到SharePreferences,以Json字符串的形式
     * @param SharedPreferences
     * @param 需要保存的数据
     */
    public static void saveCollectionMsgToPref(@NonNull SharedPreferences sharedPreferences
            , @NonNull GeneralBean.ResultsBean generalBean) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        CollectionBean collectionBean = new CollectionBean();
        collectionBean.setId(generalBean.getId());
        collectionBean.setType(generalBean.getType());
        collectionBean.setDate(getCurrentDate());
        collectionBean.setTitle(generalBean.getDesc());
        collectionBean.setUrl(generalBean.getUrl());

        Gson gson = new Gson();

        editor.putString(generalBean.getId(), gson.toJson(collectionBean));
        editor.apply();
    }

    /*
     * 根据id移除保存的信息
     * @param SharedPreferences
     * @param 需要删除数据的id
     */
    public static void removeCollectionMsg(@NonNull SharedPreferences sharedPreferences
            , @NonNull String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(id);
        editor.apply();
    }

    /*
     * 根据id检查当前的数据是否已经被收藏
     * @param SharedPreferences
     * @param id
     * @return 返回检查的结果，true为已经被收藏，false为没有被收藏
     */
    public static boolean checkMsgIsCollected(@NonNull SharedPreferences sharedPreferences
            , @NonNull String id) {
        return !(sharedPreferences.getString(id, "").equals(""));
    }

    /*
     * 得到请求数据
     * @param handler处理返回的数据
     * @param type请求类型
     * @param count请求数量
     * @param page请求页数
     */
    public static void getRequestData(final Handler handler, String type, int count, int page) {
        GankApiService service = RetrofitUtil.getRetrofit().create(GankApiService.class);
        Call<JsonObject> call = service.getResponse(type, count, page);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Message message = Message.obtain();
                message.what = Constant.REQUEST_SUCCESS;
                message.obj = response.body().toString();
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
