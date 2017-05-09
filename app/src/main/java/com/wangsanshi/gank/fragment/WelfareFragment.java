package com.wangsanshi.gank.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.wangsanshi.gank.R;
import com.wangsanshi.gank.activity.ShowImageActivity;
import com.wangsanshi.gank.adapter.WelfareRvAdapter;
import com.wangsanshi.gank.entity.Constant;
import com.wangsanshi.gank.entity.GeneralBean;
import com.wangsanshi.gank.receiver.NetworkChangeReceiver;
import com.wangsanshi.gank.util.ActivityUtil;
import com.wangsanshi.gank.util.NetworkUtil;
import com.wangsanshi.gank.util.Utility;
import com.wangsanshi.gank.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WelfareFragment extends BaseFragment {
    private static int page = 1;

    private GeneralBean.ResultsBean resultsBean;

    private List<GeneralBean> datas;

    private WelfareRvAdapter adapter;

    private NetworkChangeReceiver networkChangeReceiver;

    @BindView(R.id.rv_welfare)
    RecyclerView rvWelfare;

    @BindView(R.id.srl_welfare)
    SwipeRefreshLayout srlWelfare;

    @BindView(R.id.network_error_welfare)
    View viewNetworkError;

    public WelfareFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_welfare;
    }

    @Override
    public void initParams() {
        datas = new ArrayList<>();

        initSwipeRefreshLayout();
        initAdapter();
        initRecylerView();
        initReceiver();
    }

    /*
     * 注册广播接收者
     */
    private void initReceiver() {
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver(handler);
        getActivity().registerReceiver(networkChangeReceiver, filter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().unregisterReceiver(networkChangeReceiver);
    }

    private void initSwipeRefreshLayout() {
        srlWelfare.setRefreshing(true);
        srlWelfare.setColorSchemeResources(R.color.colorAccent);
        //设置下拉刷新监听
        srlWelfare.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkUtil.networkIsConnected(getActivity())) {
                    //有网络直接加载
                    Utility.getRequestData(handler,
                            getString(R.string.welfare),
                            Constant.DEFAULT_LOAD_WELFARE_ITEM_COUNT,
                            Constant.DEFAULT_LOAD_PAGE);
                } else {
                    //没有网络显示提示信息，并取消刷新
                    showLongSnackbar(getActivity().findViewById(R.id.dl_main)
                            , getString(R.string.network_not_connected));
                    if (srlWelfare.isRefreshing()) {
                        srlWelfare.setRefreshing(false);
                    }
                }
            }
        });
    }

    private void initRecylerView() {
        rvWelfare.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvWelfare.setAdapter(adapter);
        //为RecylerView添加滚动监听
        rvWelfare.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (ViewUtil.isSlideToBottom(rvWelfare)) {
                    if (NetworkUtil.networkIsConnected(getActivity())) {
                        //RecylerView到达底部时直接加载下一页
                        Utility.getRequestData(handler,
                                getString(R.string.welfare),
                                Constant.DEFAULT_LOAD_WELFARE_ITEM_COUNT,
                                ++page);
                    } else {
                        showLongSnackbar(getActivity().findViewById(R.id.dl_main)
                                , getString(R.string.network_not_connected));
                    }
                }
            }
        });
    }

    private void initAdapter() {
        adapter = new WelfareRvAdapter(getActivity().getApplicationContext(), datas);

        adapter.setOnItemClickListener(new WelfareRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ShowImageActivity.class);
                resultsBean = datas.get(position / Constant.DEFAULT_LOAD_WELFARE_ITEM_COUNT)
                        .getResults()
                        .get(position % Constant.DEFAULT_LOAD_WELFARE_ITEM_COUNT);
                intent.putExtra(ShowImageActivity.DATAS_IN_WELFARE, resultsBean);
                ActivityUtil.startActivityWithFadeAnim(getActivity(), intent);
            }
        });
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.REQUEST_SUCCESS:
                    setRvData((String) msg.obj);
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
     * 网络未连接时显示错误信息
     */
    private void showNetworkErrorMsg() {
        if (srlWelfare.isRefreshing()) {
            srlWelfare.setRefreshing(false);
        }
        if (datas.size() == 0) {
            viewNetworkError.setVisibility(View.VISIBLE);
            srlWelfare.setVisibility(View.GONE);
        }
        showLongSnackbar(getActivity().findViewById(R.id.dl_main)
                , getString(R.string.network_not_connected));
    }

    private void refreshData() {
        if (viewNetworkError.getVisibility() == View.VISIBLE) {
            viewNetworkError.setVisibility(View.GONE);
        }
        if (srlWelfare.getVisibility() == View.GONE) {
            srlWelfare.setVisibility(View.VISIBLE);
        }
        Utility.getRequestData(handler,
                getString(R.string.welfare),
                Constant.DEFAULT_LOAD_WELFARE_ITEM_COUNT,
                page);
    }

    /*
     * 为RecyclerView设置数据
     */
    private void setRvData(String result) {
        srlWelfare.setRefreshing(false);
        Gson gson = new Gson();
        GeneralBean generalBean = gson.fromJson(result, GeneralBean.class);//将json字符串转化为bean对象
        datas.add(generalBean);
        for (int i = (datas.size() - 1) * 10; i < datas.size() * 10; i++) {
            adapter.notifyItemInserted(i);
        }
    }

}
