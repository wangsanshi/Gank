package com.wangsanshi.gank.util;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.wangsanshi.gank.entity.CollectionBean;
import com.wangsanshi.gank.entity.GeneralBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {

    /*
     * 计算发布时间距离当前时间的长度
     */
    public static String timeFormat(@NonNull String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance();

        Date publishDate = null;
        try {
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
     */
    public static String getCurrentDate() {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-M-d");
        return spf.format(new Date());
    }

    /*
     * 保存收藏的信息到SharePreferences,以Json字符串的形式
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
     */
    public static void removeCollectionMsg(@NonNull SharedPreferences sharedPreferences
            , @NonNull String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(id);
        editor.apply();
    }

    /*
     * 根据id检查当前的信息是否已经被收藏
     * @return 返回检查的结果，true为已经被收藏，false为没有被收藏
     */
    public static boolean checkMsgIsCollected(@NonNull SharedPreferences sharedPreferences
            , @NonNull String id) {
        return !(sharedPreferences.getString(id, "").equals(""));
    }
}
