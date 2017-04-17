package com.wangsanshi.gank.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wangsanshi.gank.R;
import com.wangsanshi.gank.adapter.WelfareAdapter;
import com.wangsanshi.gank.entity.WelfareBean;
import com.wangsanshi.gank.retrofit.GankApiService;
import com.wangsanshi.gank.retrofit.RetrofitUtil;
import com.wangsanshi.gank.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelfareFragment extends BaseFragment {
    private static final String TAG = "WelfareFragment";

    private static final int DEFAULT_ITEM_COUNT = 10;

    private static final int RESULT = 0;

    private static int page = 1;

    private List<WelfareBean> datas;

    private WelfareAdapter adapter;

    @BindView(R.id.rv_welfare)
    RecyclerView rvWelfare;

    @BindView(R.id.srl_welfare)
    SwipeRefreshLayout srlWelfare;

    public WelfareFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_welfare;
    }

    @Override
    public void initParams() {
        datas = new ArrayList<>();
        srlWelfare.setRefreshing(true);
        adapter = new WelfareAdapter(getActivity(), datas);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false);
        rvWelfare.setLayoutManager(layoutManager);
        rvWelfare.setAdapter(adapter);
        rvWelfare.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();

                if (lastVisibleItem >= (totalItemCount - 1) && dy < 0) {
                    getResult(getString(R.string.welfare), DEFAULT_ITEM_COUNT, ++page);
                }
            }
        });
        if (NetworkUtil.networkIsConnected(getActivity())) {
            getResult(getString(R.string.welfare), DEFAULT_ITEM_COUNT, page);
        } else {
            srlWelfare.setRefreshing(false);
        }
        srlWelfare.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getResult(getString(R.string.welfare), DEFAULT_ITEM_COUNT, page);
            }
        });
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == RESULT) {
                setRvData((String) msg.obj);
            }
            return false;
        }
    });

    private void setRvData(String result) {
        srlWelfare.setRefreshing(false);
        Gson gson = new Gson();
        WelfareBean welfareBean = gson.fromJson(result, WelfareBean.class);
        datas.add(welfareBean);
        adapter.notifyDataSetChanged();
    }

    private void getResult(final String type, int count, int page) {
        GankApiService service = RetrofitUtil.getRetrofit().create(GankApiService.class);
        Call<JsonObject> call = service.getResponse(type, count, page);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, response.body().toString());
                Message message = Message.obtain();
                message.what = RESULT;
                message.obj = response.body().toString();
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

}
