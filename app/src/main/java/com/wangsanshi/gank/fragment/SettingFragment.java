package com.wangsanshi.gank.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;

import com.wangsanshi.gank.R;

import java.io.File;

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
                .setSummary(cacheSizeFormat(getActivity().getCacheDir().length()));
        findPreference(getString(R.string.setting_clear_cache_key))
                .setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case "qq":
                copy(getActivity(), preference.getSummary().toString());
                showSnackbar(getString(R.string.copy_success));
                break;

            case "clear_cache":
                clearCache(getActivity().getCacheDir());
                findPreference(getString(R.string.setting_clear_cache_key))
                        .setSummary(getString(R.string.setting_cache_default_value));
                showSnackbar(getString(R.string.clear_cache_success));
                break;

            default:
                break;
        }
        return false;
    }

    private void showSnackbar(String content) {
        Snackbar
                .make(getActivity().findViewById(R.id.cl_setting), content, Snackbar.LENGTH_SHORT)
                .show();
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

    /*
     *清除缓存
     */
    private void clearCache(@NonNull File file) {
        if (file.exists() && file.isDirectory()) {
            for (File itemFile : file.listFiles()) {
                if (itemFile.isDirectory()) {
                    clearCache(itemFile);
                } else {
                    itemFile.delete();
                }
            }
        }
    }

    private String cacheSizeFormat(long size) {
        if ((size / 1024) > 1024) {
            return size / (1024 * 1024) + "MB";//当文件长度大于1024KB则显示多少MB
        } else {
            return (size / 1024) + "KB";//当文件长度小于1024KB则显示为多少KB
        }
    }
}
