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
import com.wangsanshi.gank.adapter.VideoRvAdapter;
import com.wangsanshi.gank.entity.VideoBean;
import com.wangsanshi.gank.retrofit.GankApiService;
import com.wangsanshi.gank.retrofit.RetrofitUtil;
import com.wangsanshi.gank.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoFragment extends BaseFragment {
    private static final String TAG = "VideoFragment";

    private List<VideoBean> datas;

    private VideoRvAdapter adapter;

    @BindView(R.id.rv_video)
    RecyclerView rvVideo;

    @BindView(R.id.srl_video)
    SwipeRefreshLayout srlVideo;

    public VideoFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initParams() {
        datas = new ArrayList<>();
        adapter = new VideoRvAdapter(getActivity().getApplicationContext(), datas);

        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVideo.addItemDecoration(new DividerItemDecoration(getActivity()));
        rvVideo.setAdapter(adapter);

        getResult(getString(R.string.video), 10, 1);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == RetrofitUtil.RESPONSE_SUCCESS) {
                setRvData(msg.obj.toString());
            }
            return false;
        }
    });

    private void getResult(String type, int count, int page) {
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

    private void setRvData(String result) {
        Gson gson = new Gson();
        VideoBean videoBean = gson.fromJson(result, VideoBean.class);
        datas.add(videoBean);
        for (int i = (datas.size() - 1) * 10; i < datas.size() * 10; i++) {
            Log.e(TAG, "i = " + i);
            adapter.notifyItemInserted(i);
        }
    }
}
