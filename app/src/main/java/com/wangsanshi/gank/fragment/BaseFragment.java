package com.wangsanshi.gank.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    protected View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        initParams();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public abstract int getLayoutId();

    public abstract void initParams();

    public void showLongSnackbar(View view,String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_LONG).show();
    }
}
