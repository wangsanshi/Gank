package com.wangsanshi.gank.entity;

/*
 * 常量类
 */

public class Constant {
    /*
     * 网络请求成功
     */
    public static final int REQUEST_SUCCESS = 2;
    /*
     * 收藏到SharedPreferences的文件名称
     */
    public static final String COLLECTION_SPF_NAME = "collection";
    /*
     * 网络已连接
     */
    public static final int NETWORK_CONNECTED = 1;
    /*
     * 网络未连接
     */
    public static final int NETWORK_DIS_CONNECTED = -1;
    /*
     * 福利页面默认每页加载的图片数量
     */
    public static final int DEFAULT_LOAD_WELFARE_ITEM_COUNT = 10;
    /*
     * 下拉刷新默认加载第一页的数据
     */
    public static final int DEFAULT_LOAD_PAGE = 1;

    /*
     * 通用页面默认每页加载的数据个数
     */
    public static final int DEFAULT_LOAD_GENERAL_ITEM_COUNT = 5;
    /*
     * 通用页面向显示具体信息的页面传递的数据
     */
    public static final String DATAS_IN_GENERAL = "datas_in_general";
}
