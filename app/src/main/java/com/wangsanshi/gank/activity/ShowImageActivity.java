package com.wangsanshi.gank.activity;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.wangsanshi.gank.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ShowImageActivity extends BaseActivity {
    private static final int UI_ANIMATION_DELAY = 100;

    @BindView(R.id.iv_content_show)
    ImageView ivContent;

    @BindView(R.id.btn_share_show)
    ImageButton ibShare;

    @BindView(R.id.btn_collection_show)
    ImageButton ibCollection;

    @BindView(R.id.btn_download_show)
    ImageButton ibDownload;

    @BindView(R.id.ll_content_show)
    LinearLayout llContent;

    private boolean mVisible;

    private final Handler mHandler = new Handler();

    private final Runnable hideRunnable = new Runnable() {
        @Override
        public void run() {
            ObjectAnimator.ofFloat(ibShare, "alpha", 1f, 0f).setDuration(600).start();
            ObjectAnimator.ofFloat(ibCollection, "alpha", 1f, 0f).setDuration(400).start();
            ObjectAnimator.ofFloat(ibDownload, "alpha", 1f, 0f).setDuration(200).start();
        }
    };

    private final Runnable showRunnable = new Runnable() {
        @Override
        public void run() {
            if (llContent.getVisibility() != View.VISIBLE) {
                llContent.setVisibility(View.VISIBLE);
            }
            ObjectAnimator.ofFloat(ibShare, "alpha", 0f, 1f).setDuration(200).start();
            ObjectAnimator.ofFloat(ibCollection, "alpha", 0f, 1f).setDuration(400).start();
            ObjectAnimator.ofFloat(ibDownload, "alpha", 0f, 1f).setDuration(600).start();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_image;
    }

    @Override
    public void initParams() {
        mVisible = false;
        llContent.setVisibility(View.GONE);
        Glide.with(getApplicationContext())
                .load(getIntent().getExtras().getString("url"))
                .into(ivContent);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ivContent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    @OnClick(R.id.fl_show)
    public void showBtn() {
        toggle();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    /*
     * 隐藏分享，收藏，保存三个按钮
     */
    private void hide() {
        mHandler.removeCallbacks(showRunnable);
        mHandler.postDelayed(hideRunnable, UI_ANIMATION_DELAY);
        mVisible = false;
    }

    /*
     * 显示分享，收藏，保存三个按钮
     */
    private void show() {
        mHandler.removeCallbacks(hideRunnable);
        mHandler.postDelayed(showRunnable, UI_ANIMATION_DELAY);
        mVisible = true;
    }

}
