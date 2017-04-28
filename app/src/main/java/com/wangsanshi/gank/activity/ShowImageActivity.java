package com.wangsanshi.gank.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.wangsanshi.gank.R;
import com.wangsanshi.gank.fragment.WelfareFragment;
import com.wangsanshi.gank.retrofit.GankApiService;
import com.wangsanshi.gank.retrofit.RetrofitUtil;
import com.wangsanshi.gank.util.NetworkUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowImageActivity extends BaseActivity {
    private static final String TAG = "ShowImageActivity";
    /*
     * 动画的延迟时间
     */
    private static final int UI_ANIMATION_DELAY = 100;

    private static final int PERMISSION_REQUEST_CODE = 0;

    public static final String COLLECTION_SPF_NAME = "collection";

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

    private String imageUrl;

    private String imageId;

    private String imagePath;

    private ResponseBody body;

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
        imageUrl = getIntent().getExtras().getString(WelfareFragment.IMAGE_URL);
        imageId = getIntent().getExtras().getString(WelfareFragment.IMAGE_ID);

        mVisible = false;
        llContent.setVisibility(View.GONE);
        Glide.with(getApplicationContext())
                .load(imageUrl)
                .into(ivContent);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ivContent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    @OnClick(R.id.fl_show)
    public void showBtn() {
        toggle();
    }

    @OnClick(R.id.btn_share_show)
    public void share(View view) {
        if (NetworkUtil.networkIsConnected(this)) {
            downLoadImageToDisk(imageUrl);
            Intent intent = new Intent(Intent.ACTION_SEND);
            Uri uri = android.net.Uri.fromFile(new File(imagePath));
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setType("image/*");
            Log.e(TAG, imagePath);
            startActivity(Intent.createChooser(intent, "share"));
        } else {
            showShortToast(getString(R.string.network_not_connected));
        }
    }

    @OnClick(R.id.btn_collection_show)
    public void collection(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(COLLECTION_SPF_NAME, MODE_PRIVATE).edit();
        editor.putString(imageId, imageUrl);
        editor.apply();
        showShortToast(getString(R.string.collection_success));
    }

    @OnClick(R.id.btn_download_show)
    public void download(View view) {
        if (NetworkUtil.networkIsConnected(this)) {
            downLoadImageToDisk(imageUrl);
            showShortToast(getString(R.string.download_success));
        } else {
            showShortToast(getString(R.string.network_not_connected));
        }
    }

    /*
     * 下载图片
     */
    private void downLoadImageToDisk(String imageUrl) {
        GankApiService service = RetrofitUtil.getRetrofit().create(GankApiService.class);
        Call<ResponseBody> call = service.downLoadImage(imageUrl);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (ContextCompat.checkSelfPermission(ShowImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ShowImageActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_CODE);
                } else {
                    body = response.body();
                    saveImage(body);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showShortToast(getString(R.string.download_failure));
            }
        });
    }

    /*
     * 将下载的图片保存到SD卡
     */
    private void saveImage(ResponseBody body) {
        imagePath = getExternalFilesDir(null) + File.separator + imageId + ".jpg";
        File imageFile = new File(imagePath);
        Log.e(TAG, getExternalFilesDir(null) + File.separator + imageId + ".jpg");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = body.byteStream();
            outputStream = new FileOutputStream(imageFile);

            int temp;

            while ((temp = inputStream.read()) != -1) {
                outputStream.write(temp);
            }

            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveImage(body);
            } else {
                showShortToast(getString(R.string.permission_deny));
            }
        }
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
