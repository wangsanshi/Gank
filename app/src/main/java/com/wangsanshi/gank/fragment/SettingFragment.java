package com.wangsanshi.gank.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.wangsanshi.gank.R;

public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_setting);

        initParams();
    }

    private void initParams() {

    }
}
