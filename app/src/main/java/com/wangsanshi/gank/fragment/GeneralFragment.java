package com.wangsanshi.gank.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wangsanshi.gank.R;
import com.wangsanshi.gank.adapter.GeneralAdapter;
import com.wangsanshi.gank.entity.GeneralBean;
import com.wangsanshi.gank.retrofit.GankApiService;
import com.wangsanshi.gank.retrofit.RetrofitUtil;
import com.wangsanshi.gank.util.NetworkUtil;
import com.wangsanshi.gank.util.ViewUtil;
import com.wangsanshi.gank.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralFragment extends BaseFragment {
    private static final String TAG = "GeneralFragment";

    private static final String FRAGMENT_TYPE = "fragment_type";

    private static final int DEFAULT_PAGE = 1;

    private static int page = 1;

    private String currentType;

    @BindView(R.id.rv_general)
    RecyclerView recyclerView;

    @BindView(R.id.srl_general)
    SwipeRefreshLayout refreshLayout;

    private GeneralAdapter adapter;

    private List<GeneralBean> datas;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == RetrofitUtil.RESPONSE_SUCCESS) {
                refreshLayout.setRefreshing(false);
                setDataToRv(msg.obj.toString());
            }
            return false;
        }
    });

    public GeneralFragment() {
    }

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

        currentType = getArguments().getString(FRAGMENT_TYPE);

        refreshLayout.setRefreshing(true);
        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        refreshLayout.setSize(SwipeRefreshLayout.LARGE);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clearDatas();
                checkNetwork(currentType, GeneralAdapter.DEFAULT_ITEM_COUNT, DEFAULT_PAGE);
            }
        });

        datas = new ArrayList<>();
        adapter = new GeneralAdapter(getActivity(), datas);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (ViewUtil.isSlideToBottom(recyclerView)) {
                    checkNetwork(currentType, GeneralAdapter.DEFAULT_ITEM_COUNT, ++page);
                }
            }
        });

        checkNetwork(currentType, GeneralAdapter.DEFAULT_ITEM_COUNT, DEFAULT_PAGE);
    }

    private void checkNetwork(String type, int count, int page) {
        if (NetworkUtil.networkIsConnected(getActivity().getApplicationContext())) {
            loadDatas(type, count, page);
        } else {
            refreshLayout.setRefreshing(false);
            showLongSnackbar(getActivity().findViewById(R.id.dl_main), getString(R.string.network_not_connected));
        }
    }

    private void loadDatas(String type, int count, int page) {
        GankApiService service = RetrofitUtil.getRetrofit().create(GankApiService.class);
        Call<JsonObject> call = service.getResponse(type, count, page);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Message message = Message.obtain();
                message.what = RetrofitUtil.RESPONSE_SUCCESS;
                message.obj = response.body().toString();
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void setDataToRv(String data) {
        Gson gson = new Gson();
        GeneralBean generalBean = gson.fromJson(data, GeneralBean.class);
        datas.add(generalBean);
        for (int i = (datas.size() - 1) * GeneralAdapter.DEFAULT_ITEM_COUNT;
             i < datas.size() * GeneralAdapter.DEFAULT_ITEM_COUNT; i++) {
            adapter.notifyItemInserted(i);
        }
    }
}
