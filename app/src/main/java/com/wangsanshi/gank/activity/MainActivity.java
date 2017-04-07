package com.wangsanshi.gank.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangsanshi.gank.R;
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
    /*
     * welfare fragment索引
     */
    private static final int FRAGMENT_WELFARE_INDEX = 0;
    /*
     * video fragment索引
     */
    private static final int FRAGMENT_VIDEO_INDEX = 1;
    /*
     * recommend fragment索引
     */
    private static final int FRAGMENT_RECOMMEND_INDEX = 2;
    /*
     * app fragment索引
     */
    private static final int FRAGMENT_APP_INDEX = 3;
    /*
     * resource fragment索引
     */
    private static final int FRAGMENT_RESOURCE_INDEX = 4;
    /*
     * android fragment索引
     */
    private static final int FRAGMENT_ANDROID_INDEX = 5;
    /*
     * ios fragment索引
     */
    private static final int FRAGMENT_IOS_INDEX = 6;
    /*
     * front fragment索引
     */
    private static final int FRAGMENT_FRONT_INDEX = 7;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case FRAGMENT_WELFARE_INDEX:
                    return new WelfareFragment();
                case FRAGMENT_VIDEO_INDEX:
                    return new VideoFragment();
                case FRAGMENT_RECOMMEND_INDEX:
                    return new RecommendFragment();
                case FRAGMENT_APP_INDEX:
                    return new AppFragment();
                case FRAGMENT_RESOURCE_INDEX:
                    return new ResourceFragment();
                case FRAGMENT_ANDROID_INDEX:
                    return new AndroidFragment();
                case FRAGMENT_IOS_INDEX:
                    return new IosFragment();
                case FRAGMENT_FRONT_INDEX:
                    return new FrontFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case FRAGMENT_WELFARE_INDEX:
                    return getString(R.string.welfare);
                case FRAGMENT_VIDEO_INDEX:
                    return getString(R.string.video);
                case FRAGMENT_RECOMMEND_INDEX:
                    return getString(R.string.recommend);
                case FRAGMENT_APP_INDEX:
                    return getString(R.string.app);
                case FRAGMENT_RESOURCE_INDEX:
                    return getString(R.string.resource);
                case FRAGMENT_ANDROID_INDEX:
                    return getString(R.string.android);
                case FRAGMENT_IOS_INDEX:
                    return getString(R.string.ios);
                case FRAGMENT_FRONT_INDEX:
                    return getString(R.string.front);

            }
            return null;
        }
    }
}
