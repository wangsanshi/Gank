package com.wangsanshi.gank.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.wangsanshi.gank.entity.Constant;
import com.wangsanshi.gank.util.NetworkUtil;

/*
 * 网络状态改变的广播接收者
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    private Handler handler;

    public NetworkChangeReceiver(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (NetworkUtil.networkIsConnected(context)) {
            handler.sendEmptyMessage(Constant.NETWORK_CONNECTED);
        } else {
            handler.sendEmptyMessage(Constant.NETWORK_DIS_CONNECTED);
        }
    }
}
