package com.wangsanshi.gank.util;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.wangsanshi.gank.R;

public class ActivityUtil {
    /*
     * 以淡入淡出的方式结束当前Activity
     */
    public static void finishActivityWithFadeAnim(@NonNull Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }

    /*
     * 以淡入淡出的方式启动Activity
     */
    public static void startActivityWithFadeAnim(@NonNull Activity activity, @NonNull Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }

    /*
     * 以拉入拉出的方式结束Activity
     */
    public static void finishActivityWithPushAnim(@NonNull Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.activity_pull_in_left, R.anim.activity_push_out_right);
    }

    /*
     * 以拉入拉出的方式启动Activity
     */
    public static void startActivityWithPullAnim(@NonNull Activity activity, @NonNull Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_pull_in_right, R.anim.activity_push_out_left);
    }
}
