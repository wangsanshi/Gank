package com.wangsanshi.gank.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;

import com.wangsanshi.gank.R;

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_setting);

        initParams();
    }

    private void initParams() {
        initClearCachePref();
        initQqPref();
        initVersionPref();
    }

    /*
     * 得到当前的版本信息
     */
    private void initVersionPref() {
        String versionName = "";
        try {
            versionName = getActivity()
                    .getPackageManager()
                    .getPackageInfo(getActivity().getPackageName(), 0)
                    .versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        findPreference(getString(R.string.setting_version_key)).setSummary(versionName);
    }

    private void initQqPref() {
        findPreference(getString(R.string.setting_qq_key))
                .setOnPreferenceClickListener(this);
    }

    private void initClearCachePref() {
        findPreference(getString(R.string.setting_clear_cache_key))
                .setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case "qq":
                copy(getActivity(), preference.getSummary().toString());
                Snackbar.make(getActivity().findViewById(R.id.cl_setting)
                        , getString(R.string.copy_success)
                        , Snackbar.LENGTH_SHORT).show();
                break;

            case "clear_cache":

                break;

            default:
                break;
        }
        return false;
    }

    private void showSnackbar() {

    }

    /*
     * 复制到剪贴板
     */
    @SuppressWarnings("deprecation")
    private void copy(Context context, String content) {
        ClipboardManager clipboardManager = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setText(content.trim());
    }
}
