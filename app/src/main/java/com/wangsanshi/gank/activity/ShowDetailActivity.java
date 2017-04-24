package com.wangsanshi.gank.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.wangsanshi.gank.R;

import butterknife.BindView;

public class ShowDetailActivity extends BaseActivity {
    private static final String TAG = "ShowDetailActivity";
    @BindView(R.id.toolbar_show_detail)
    Toolbar toolbar;

    @BindView(R.id.fab_show_detail)
    FloatingActionButton fab;

    @BindView(R.id.wv_show_detail)
    WebView webView;

    @BindView(R.id.tv_desc_show_detail)
    TextView tvDesc;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_detail;
    }

    @Override
    public void initParams() {
        toolbar.setTitle(getIntent().getExtras().getString("TYPE"));
        tvDesc.setText(getIntent().getExtras().getString("DESC"));
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(getIntent().getExtras().getString("URL"));
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
