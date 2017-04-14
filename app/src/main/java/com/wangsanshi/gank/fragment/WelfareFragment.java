package com.wangsanshi.gank.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.wangsanshi.gank.R;
import com.wangsanshi.gank.adapter.WelfareAdapter;
import com.wangsanshi.gank.entity.WelfareBean;
import com.wangsanshi.gank.retrofit.GankApiService;
import com.wangsanshi.gank.retrofit.RetrofitUtil;

import java.io.StringReader;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelfareFragment extends BaseFragment {
    private static final String TAG = "WelfareFragment";

    private static final int DEFAULT_DATA_COUNT = 10;

    private static final int DEFAULT_DATA_PAGE = 1;

    private WelfareBean welfareBean;

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
        getResult(getString(R.string.welfare), DEFAULT_DATA_COUNT, DEFAULT_DATA_PAGE);
        //Log.e(TAG, "result:" + result);
//        if (!result.equals("")) {
//            Gson gson = new Gson();
//            welfareBean = gson.fromJson(result, WelfareBean.class);
//            adapter = new WelfareAdapter(getActivity(), welfareBean);
//        }
//
//        rvWelfare.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rvWelfare.setAdapter(adapter);
    }

    private void getResult(final String type, int count, int page) {
        GankApiService service = RetrofitUtil.getRetrofit().create(GankApiService.class);
        Call<Object> call = service.getResponse(type, count, page);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                //Log.e(TAG, response.body().toString());
                String result = response.body().toString();
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(result));
                reader.setLenient(true);
                welfareBean = gson.fromJson(reader, WelfareBean.class);
                adapter = new WelfareAdapter(getActivity(), welfareBean);
                rvWelfare.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvWelfare.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getMessage());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

}
