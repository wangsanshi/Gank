package com.wangsanshi.gank.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wangsanshi.gank.R;
import com.wangsanshi.gank.fragment.SettingFragment;

import butterknife.BindView;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.tb_setting)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initParams() {
        initToolbar();
        initFragment();
    }

    private void initFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_setting, new SettingFragment())
                .commit();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishActivity();
    }

    /*
     * 退出当前Activity
     */
    private void finishActivity() {
        finish();
        overridePendingTransition(R.anim.activity_pull_in_left, R.anim.activity_push_out_right);
    }
}
