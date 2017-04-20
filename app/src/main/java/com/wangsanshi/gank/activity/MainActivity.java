package com.wangsanshi.gank.activity;

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
import com.wangsanshi.gank.fragment.AndroidFragment;
import com.wangsanshi.gank.fragment.AppFragment;
import com.wangsanshi.gank.fragment.FrontFragment;
import com.wangsanshi.gank.fragment.IosFragment;
import com.wangsanshi.gank.fragment.RecommendFragment;
import com.wangsanshi.gank.fragment.ResourceFragment;
import com.wangsanshi.gank.fragment.VideoFragment;
import com.wangsanshi.gank.fragment.WelfareFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
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
        setSupportActionBar(toolbar);

        nvMain.setNavigationItemSelectedListener(new NavigationItemSelected());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                dlMain,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        dlMain.setDrawerListener(toggle);
        toggle.syncState();

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class NavigationItemSelected implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_today:

                    break;

                case R.id.nav_collection:

                    break;

                case R.id.nav_setting:

                    break;

                default:
                    break;
            }
            return true;
        }
    }
}
