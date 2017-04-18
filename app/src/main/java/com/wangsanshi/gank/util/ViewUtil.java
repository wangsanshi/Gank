package com.wangsanshi.gank.util;

import android.support.v7.widget.RecyclerView;

public class ViewUtil {
    /*
     * 判断recyclerview是否到底部
     */
    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

}
