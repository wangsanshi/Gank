package com.wangsanshi.gank.fragment;


import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.wangsanshi.gank.R;
import com.wangsanshi.gank.adapter.GeneralAdapter;
import com.wangsanshi.gank.entity.Constant;
import com.wangsanshi.gank.entity.GeneralBean;
import com.wangsanshi.gank.receiver.NetworkChangeReceiver;
import com.wangsanshi.gank.util.NetworkUtil;
import com.wangsanshi.gank.util.Utility;
import com.wangsanshi.gank.util.ViewUtil;
import com.wangsanshi.gank.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GeneralFragment extends BaseFragment {
    private static final String FRAGMENT_TYPE = "fragment_type";

    private static int page = 1;

    private String currentType;

    private NetworkChangeReceiver networkChangeReceiver;

    @BindView(R.id.rv_general)
    RecyclerView recyclerView;

    @BindView(R.id.srl_general)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.network_error_general)
    View viewNetworkError;

    private GeneralAdapter adapter;

    private List<GeneralBean> datas;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.REQUEST_SUCCESS:
                    setDataToRv(msg.obj.toString());
                    break;

                case Constant.NETWORK_CONNECTED:
                    refreshData();
                    break;
                case Constant.NETWORK_DIS_CONNECTED:
                    showNetworkErrorMsg();
                    break;
            }

            return false;
        }
    });

    /*
     * 网络未连接显示错误信息
     */
    private void showNetworkErrorMsg() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (datas.size() == 0) {
            viewNetworkError.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);
        }
        showLongSnackbar(getActivity().findViewById(R.id.dl_main)
                , getString(R.string.network_not_connected));
    }

    /*
     * 刷新数据
     */
    private void refreshData() {
        if (viewNetworkError.getVisibility() == View.VISIBLE) {
            viewNetworkError.setVisibility(View.GONE);
        }
        if (swipeRefreshLayout.getVisibility() == View.GONE) {
            swipeRefreshLayout.setVisibility(View.VISIBLE);
        }
        Utility.getRequestData(handler
                , currentType
                , Constant.DEFAULT_LOAD_GENERAL_ITEM_COUNT
                , Constant.DEFAULT_LOAD_PAGE);
    }

    public GeneralFragment() {
    }

    /*
     * 根据页面类型得到Fragment实例
     */
    public static GeneralFragment newInstance(String fragmentType) {
        GeneralFragment generalFragment = new GeneralFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FRAGMENT_TYPE, fragmentType);
        generalFragment.setArguments(bundle);
        return generalFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_general;
    }

    @Override
    public void initParams() {
        datas = new ArrayList<>();
        currentType = getArguments().getString(FRAGMENT_TYPE);//得到当前Fragment的类型
        adapter = new GeneralAdapter(getActivity(), datas);

        initReceiver();
        initSwipeRefreshLayout();
        initRecyclerView();
    }

    /*
     * 注册网络变化的广播接收者
     */
    private void initReceiver() {
        networkChangeReceiver = new NetworkChangeReceiver(handler);
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        getActivity().registerReceiver(networkChangeReceiver, filter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().unregisterReceiver(networkChangeReceiver);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.setAdapter(adapter);
        //为RecyclerView添加滚动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (ViewUtil.isSlideToBottom(recyclerView)) {
                    //RecyclerView到达底部直接加载下一页
                    if (NetworkUtil.networkIsConnected(getActivity())) {
                        Utility
                                .getRequestData(handler
                                        , currentType
                                        , Constant.DEFAULT_LOAD_GENERAL_ITEM_COUNT
                                        , ++page);
                    } else {
                        showLongSnackbar(getActivity().findViewById(R.id.dl_main), getString(R.string.network_not_connected));
                    }
                }
            }
        });
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        //为SwipeRefreshLayout设置刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkUtil.networkIsConnected(getActivity())) {
                    if (adapter.getItemCount() != 0) {
                        adapter.clearDatas();
                    }
                    Utility
                            .getRequestData(handler
                                    , currentType
                                    , Constant.DEFAULT_LOAD_GENERAL_ITEM_COUNT
                                    , Constant.DEFAULT_LOAD_PAGE);
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    showLongSnackbar(getActivity().findViewById(R.id.dl_main), getString(R.string.network_not_connected));
                }
            }
        });
    }

    private void setDataToRv(String data) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        Gson gson = new Gson();
        GeneralBean generalBean = gson.fromJson(data, GeneralBean.class);
        datas.add(generalBean);
        for (int i = (datas.size() - 1) * Constant.DEFAULT_LOAD_GENERAL_ITEM_COUNT
             ; i < datas.size() * Constant.DEFAULT_LOAD_GENERAL_ITEM_COUNT
                ; i++) {
            adapter.notifyItemInserted(i);
        }
    }
}
