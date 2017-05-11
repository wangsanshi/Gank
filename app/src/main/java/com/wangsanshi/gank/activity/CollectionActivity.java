package com.wangsanshi.gank.activity;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wangsanshi.gank.ItemTouchHelperCallback;
import com.wangsanshi.gank.R;
import com.wangsanshi.gank.adapter.CollectionRvAdapter;
import com.wangsanshi.gank.entity.CollectionBean;
import com.wangsanshi.gank.entity.Constant;
import com.wangsanshi.gank.util.ActivityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class CollectionActivity extends BaseActivity {
    @BindView(R.id.rv_collection)
    RecyclerView recyclerView;

    @BindView(R.id.tb_collection)
    Toolbar toolbar;

    @BindView(R.id.tv_empty_data_collection)
    TextView tvEmptyData;

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
        initViewState();
        initAdapter();
        initRecyclerView();
    }

    /*
     * 设置RecyclerView和EmptyView是否可见
     */
    private void initViewState() {
        if (datas.size() == 0) {
            tvEmptyData.setVisibility(View.VISIBLE);//当无数据时，显示EmptyView
        } else {
            recyclerView.setVisibility(View.VISIBLE);//当有数据时，显示RecyclerView
        }
    }

    /*
     * 从SharedPreferences获取已经收藏的数据
     */
    private void initDatas() {
        SharedPreferences spf = getSharedPreferences(Constant.COLLECTION_SPF_NAME, MODE_PRIVATE);
        datas = new ArrayList<>();
        Gson gson = new Gson();

        Map<String, ?> allDatas = spf.getAll();//从SharedPreferences中读取收藏的所有数据
        for (Map.Entry<String, ?> me : allDatas.entrySet()) {
            CollectionBean collectionBean = gson
                    .fromJson(me.getValue().toString(), CollectionBean.class);
            datas.add(collectionBean);
        }
    }

    /*
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

    private void initAdapter() {
        adapter = new CollectionRvAdapter(this, datas);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        ActivityUtil.finishActivityWithPushAnim(this);
    }
}
