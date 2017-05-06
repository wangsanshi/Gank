package com.wangsanshi.gank.activity;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.wangsanshi.gank.R;
import com.wangsanshi.gank.adapter.CollectionRvAdapter;
import com.wangsanshi.gank.entity.CollectionBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class CollectionActivity extends BaseActivity {
    private static final String TAG = "CollectionActivity";
    @BindView(R.id.rv_collection)
    RecyclerView recyclerView;

    @BindView(R.id.tb_collection)
    Toolbar toolbar;

    private CollectionRvAdapter adapter;

    private List<CollectionBean> datas;

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initParams() {
        initToolbar();
        initDatas();
        initAdapter();
        initRecyclerView();
    }

    private void initDatas() {
        SharedPreferences spf = getSharedPreferences(ShowImageActivity.COLLECTION_SPF_NAME, MODE_PRIVATE);
        datas = new ArrayList<>();
        Gson gson = new Gson();

        Map<String, String> allDatas = (Map<String, String>) spf.getAll();
        for (Map.Entry<String, String> me : allDatas.entrySet()) {
            CollectionBean collectionBean = gson.fromJson(me.getValue(), CollectionBean.class);
            datas.add(collectionBean);
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initAdapter() {
        adapter = new CollectionRvAdapter(this, datas);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.activity_pull_in_left, R.anim.activity_push_out_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_pull_in_left, R.anim.activity_push_out_right);
    }
}
