package com.wangsanshi.gank.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    /*
     * 检查网络是否可用
     * @param 上下文Context
     * @return true为网络连接,false
     */
    public static boolean networkIsConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isAvailable());
    }

}
