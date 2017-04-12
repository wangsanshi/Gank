package com.wangsanshi.gank.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wangsanshi.gank.R;
import com.wangsanshi.gank.adapter.SectionsPagerAdapter;
import com.wangsanshi.gank.fragment.AndroidFragment;
import com.wangsanshi.gank.fragment.AppFragment;
import com.wangsanshi.gank.fragment.FrontFragment;
import com.wangsanshi.gank.fragment.IosFragment;
import com.wangsanshi.gank.fragment.RecommendFragment;
import com.wangsanshi.gank.fragment.ResourceFragment;
import com.wangsanshi.gank.fragment.VideoFragment;
import com.wangsanshi.gank.fragment.WelfareFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initParams() {
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mSectionsPagerAdapter.addFragment(new WelfareFragment(), getString(R.string.welfare));
        mSectionsPagerAdapter.addFragment(new VideoFragment(), getString(R.string.video));
        mSectionsPagerAdapter.addFragment(new RecommendFragment(), getString(R.string.recommend));
        mSectionsPagerAdapter.addFragment(new AppFragment(), getString(R.string.app));
        mSectionsPagerAdapter.addFragment(new ResourceFragment(), getString(R.string.resource));
        mSectionsPagerAdapter.addFragment(new AndroidFragment(), getString(R.string.android));
        mSectionsPagerAdapter.addFragment(new IosFragment(), getString(R.string.ios));
        mSectionsPagerAdapter.addFragment(new FrontFragment(), getString(R.string.front));

        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.fab)
    public void save(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
