package com.wangsanshi.gank.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wangsanshi.gank.R;
import com.wangsanshi.gank.adapter.SectionsPagerAdapter;
import com.wangsanshi.gank.fragment.GeneralFragment;
import com.wangsanshi.gank.fragment.WelfareFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    public SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.container_main)
    ViewPager mViewPager;

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

    @BindView(R.id.tabs_main)
    TabLayout tabLayout;

    @BindView(R.id.dl_main)
    DrawerLayout dlMain;

    @BindView(R.id.nv_main)
    NavigationView nvMain;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initParams() {
        initToolbar();
        initNavigation();
        initDrawer();
        initAdapter();
    }

    private void initNavigation() {
        nvMain.setNavigationItemSelectedListener(new NavigationItemSelected());
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                dlMain,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        dlMain.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initAdapter() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mSectionsPagerAdapter.addTitle(getString(R.string.welfare));
        mSectionsPagerAdapter.addTitle(getString(R.string.video));
        mSectionsPagerAdapter.addTitle(getString(R.string.recommend));
        mSectionsPagerAdapter.addTitle(getString(R.string.app));
        mSectionsPagerAdapter.addTitle(getString(R.string.resource));
        mSectionsPagerAdapter.addTitle(getString(R.string.android));
        mSectionsPagerAdapter.addTitle(getString(R.string.ios));
        mSectionsPagerAdapter.addTitle(getString(R.string.front));

        mSectionsPagerAdapter.addFragment(new WelfareFragment());
        mSectionsPagerAdapter.addFragment(GeneralFragment.newInstance(getString(R.string.video)));
        mSectionsPagerAdapter.addFragment(GeneralFragment.newInstance(getString(R.string.recommend)));
        mSectionsPagerAdapter.addFragment(GeneralFragment.newInstance(getString(R.string.app)));
        mSectionsPagerAdapter.addFragment(GeneralFragment.newInstance(getString(R.string.resource)));
        mSectionsPagerAdapter.addFragment(GeneralFragment.newInstance(getString(R.string.android)));
        mSectionsPagerAdapter.addFragment(GeneralFragment.newInstance(getString(R.string.ios)));
        mSectionsPagerAdapter.addFragment(GeneralFragment.newInstance(getString(R.string.front)));

        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        if (dlMain.isDrawerOpen(GravityCompat.START)) {
            dlMain.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class NavigationItemSelected implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_today:
                    dlMain.closeDrawers();
                    break;

                case R.id.nav_collection:

                    break;

                case R.id.nav_setting:
                    Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(intent);
                    break;

                default:
                    break;
            }
            return true;
        }
    }
}
