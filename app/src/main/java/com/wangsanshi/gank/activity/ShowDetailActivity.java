package com.wangsanshi.gank.activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.wangsanshi.gank.R;
import com.wangsanshi.gank.entity.GeneralBean;

import butterknife.BindView;
import butterknife.OnClick;

public class ShowDetailActivity extends BaseActivity {
    private static final String TAG = "ShowDetailActivity";

    public static final String DATAS_IN_GENERAL = "datas_in_general";

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
    /*
     * 是否已经收藏
     */
    private boolean isCollection;

    private GeneralBean.ResultsBean resultsBean;

    private SharedPreferences spf;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_detail;
    }

    @Override
    public void initParams() {
        resultsBean = getIntent().getExtras().getParcelable(DATAS_IN_GENERAL);

        initFabState();
        initToolBar();
        initWebViewSettings();
        initWebView();
    }

    /*
     * 设置WebView加载的url，设置其WebViewClient
     */
    private void initWebView() {
        webView.loadUrl(resultsBean.getUrl());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }
        });
    }

    /*
     * 设置Toolbar的大标题以及小标题的内容
     */
    private void initToolBar() {
        tvDesc.setText(resultsBean.getDesc());
        toolbar.setTitle(resultsBean.getType());
        setSupportActionBar(toolbar);
    }

    /*
     * 从SharedPreferences读取FoatingActionButton的状态
     */
    private void initFabState() {
        spf = getSharedPreferences(ShowImageActivity.COLLECTION_SPF_NAME, MODE_PRIVATE);
        Log.e(TAG, spf.getString(resultsBean.getId(), ""));
        if (!spf.getString(resultsBean.getId(), "").equals("")) {
            fab.setImageResource(R.drawable.ic_fab_pressed);
            isCollection = true;
        } else {
            fab.setImageResource(R.drawable.ic_fab_normal);
            isCollection = false;
        }
    }

    private void initWebViewSettings() {
        WebSettings settings = webView.getSettings();
        //支持获取手势焦点
        webView.requestFocusFromTouch();
        //支持JS
        settings.setJavaScriptEnabled(true);
        //设置适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //支持缩放
        settings.setSupportZoom(false);
        //隐藏原生的缩放控件
        settings.setDisplayZoomControls(false);
        //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.supportMultipleWindows();
        settings.setSupportMultipleWindows(true);
        //设置缓存模式
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);//开启DOM缓存
        settings.setAppCachePath(webView.getContext().getCacheDir().getAbsolutePath());

        //设置可访问文件
        settings.setAllowFileAccess(true);
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true);
        //支持自动加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setNeedInitialFocus(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("UTF-8");
    }

    @OnClick(R.id.fab_show_detail)
    public void collection(View view) {
        SharedPreferences.Editor editor = spf.edit();
        if (!isCollection) {
            editor.putString(resultsBean.getId(), resultsBean.getUrl());
            fab.setImageResource(R.drawable.ic_fab_pressed);
            showShortSnackbar(view, getString(R.string.collection_success));
            isCollection = true;
        } else {
            editor.remove(resultsBean.getId());
            fab.setImageResource(R.drawable.ic_fab_normal);
            showShortSnackbar(view, getString(R.string.cancel_collection));
            isCollection = false;
        }
        editor.apply();
    }

    /*
     * 若WebView有可回退的历史记录，点击返回键则返回上一页；否则，退出当前Activity
     */
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
