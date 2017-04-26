package com.wangsanshi.gank.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
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

    @BindView(R.id.app_bar_show_detail)
    AppBarLayout appBarLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_detail;
    }

    @Override
    public void initParams() {

        tvDesc.setText(getIntent().getExtras().getString("DESC"));
        toolbar.setTitle(getIntent().getExtras().getString("TYPE"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
            }
        });

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setDomStorageEnabled(true);
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
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }
}
