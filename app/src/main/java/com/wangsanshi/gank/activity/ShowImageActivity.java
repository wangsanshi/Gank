package com.wangsanshi.gank.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.wangsanshi.gank.R;
import com.wangsanshi.gank.entity.Constant;
import com.wangsanshi.gank.entity.GeneralBean;
import com.wangsanshi.gank.retrofit.GankApiService;
import com.wangsanshi.gank.retrofit.RetrofitUtil;
import com.wangsanshi.gank.util.ActivityUtil;
import com.wangsanshi.gank.util.NetworkUtil;
import com.wangsanshi.gank.util.Utility;

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
    /*
     * 动画的延迟时间
     */
    private static final int UI_ANIMATION_DELAY = 100;

    public static final String DATAS_IN_WELFARE = "datas_in_welfare";

    private static final int PERMISSION_REQUEST_CODE = 0;

    private static final int GET_RESPONSE_BODY = 1;

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

    private GeneralBean.ResultsBean resultsBean;

    private String imagePath;

    private ResponseBody responseBody;

    private ProgressDialog progressDialog;

    private SharedPreferences spf;

    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == GET_RESPONSE_BODY) {
                new SaveImageToDiskTask().execute(responseBody);
            }
            return false;
        }
    });

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
        resultsBean = getIntent().getExtras().getParcelable(DATAS_IN_WELFARE);
        spf = getSharedPreferences(Constant.COLLECTION_SPF_NAME, MODE_PRIVATE);

        initActionBar();
        initIvContent();
        initLlContent();
    }

    /*
     * 初始化分享、收藏、分享三个按钮的状态
     */
    private void initLlContent() {
        mVisible = false;
        llContent.setVisibility(View.GONE);
    }

    private void initIvContent() {
        Glide.with(getApplicationContext())
                .load(resultsBean.getUrl())
                .into(ivContent);

        ivContent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @OnClick(R.id.fl_show)
    public void showBtn() {
        toggle();
    }

    /*
     * 分享图片
     */
    @OnClick(R.id.btn_share_show)
    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, resultsBean.getDesc());
        intent.putExtra(Intent.EXTRA_TEXT, resultsBean.getUrl());
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }

    /*
     * 收藏图片，保存至SharePreferences
     */
    @OnClick(R.id.btn_collection_show)
    public void collection(View view) {
        if (Utility.checkMsgIsCollected(spf, resultsBean.getId())) {
            showShortToast(getString(R.string.already_collection));
        } else {
            Utility.saveCollectionMsgToPref(spf, resultsBean);
            showShortToast(getString(R.string.collection_success));
        }

    }

    @OnClick(R.id.btn_download_show)
    public void download(View view) {
        if (NetworkUtil.networkIsConnected(this)) {
            downLoadImage(resultsBean.getUrl());
        } else {
            showShortToast(getString(R.string.network_not_connected));
        }
    }

    /*
     * 下载图片
     */
    private void downLoadImage(String imageUrl) {
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
                    responseBody = response.body();
                    mHandler.sendEmptyMessage(GET_RESPONSE_BODY);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showShortToast(getString(R.string.download_failure));
            }
        });
    }

    /*
     * 请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode
            , @NonNull String[] permissions
            , @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mHandler.sendEmptyMessage(GET_RESPONSE_BODY);
            } else {
                showShortToast(getString(R.string.permission_deny));
            }
        }
    }

    @Override
    public void onBackPressed() {
        ActivityUtil.finishActivityWithFadeAnim(this);
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


    private class SaveImageToDiskTask extends AsyncTask<ResponseBody, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            initDialog();
            imagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    + File.separator
                    + resultsBean.getId()
                    + ".jpg";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Boolean doInBackground(ResponseBody... params) {
            ResponseBody body = params[0];
            File imageFile = new File(imagePath);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            long imageFileLength = body.contentLength();

            try {
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(imageFile);

                int temp, progress;
                long saveTotalLength = 0;
                while ((temp = inputStream.read()) != -1) {
                    ++saveTotalLength;
                    outputStream.write(temp);
                    progress = (int) ((saveTotalLength / (float) imageFileLength) * 100);
                    publishProgress(progress);
                }
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
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressDialog.dismiss();
            showShortToast(getString(R.string.download_success));
        }
    }

    /*
     *初始化ProgressDialog
     */
    private void initDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMax(100);
        progressDialog.setTitle(getString(R.string.dialog_prompt));
        progressDialog.setMessage(getString(R.string.dialog_downloading));
        progressDialog.setProgress(0);
        progressDialog.show();
    }

}
