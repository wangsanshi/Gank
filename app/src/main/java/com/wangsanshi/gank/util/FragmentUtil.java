package com.wangsanshi.gank.util;

public class FragmentUtil {

    /*
     * 得到fragment的tag
     */
    public static String getFragmentTag(int viewId, long positon) {
        return "android:switcher:" + viewId + ":" + positon;
    }

}
