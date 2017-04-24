package com.wangsanshi.gank.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangsanshi.gank.R;
import com.wangsanshi.gank.activity.ShowDetailActivity;
import com.wangsanshi.gank.entity.VideoBean;
import com.wangsanshi.gank.fragment.VideoFragment;
import com.wangsanshi.gank.util.CharacterUtil;

import java.util.List;

public class VideoRvAdapter extends RecyclerView.Adapter<VideoRvAdapter.VideoViewHolder> {
    private static final String TAG = "VideoRvAdapter";
    private Context mContext;
    private LayoutInflater mInflater;
    private List<VideoBean> mDatas;

    public VideoRvAdapter(Context mContext, List<VideoBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHolder(mInflater.inflate(R.layout.item_rv_video, parent, false));
    }

    @Override
    public void onBindViewHolder(final VideoViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final int page = position / VideoFragment.DEFAULT_VIDEO_ITEM_COUNT;
        final int positionInPage = position % VideoFragment.DEFAULT_VIDEO_ITEM_COUNT;

        holder.itemTvDesc.setText(mDatas.get(page).getResults().get(positionInPage).getDesc());
        Log.e(TAG, "desc : " + mDatas.get(page).getResults().get(positionInPage).getDesc());
        if (mDatas.get(page).getResults().get(positionInPage).getWho() != null) {
            holder.itemTvWho.setText(mContext.getString(R.string.item_who_format,
                    mDatas.get(page).getResults().get(positionInPage).getWho(),
                    CharacterUtil.timeFormat(mDatas.get(page).getResults().get(positionInPage).getPublishedAt())));
            Log.e(TAG, "qwho : " + mContext.getString(R.string.item_who_format,
                    mDatas.get(page).getResults().get(positionInPage).getWho(),
                    CharacterUtil.timeFormat(mDatas.get(page).getResults().get(positionInPage).getPublishedAt())));
        } else {
            holder.itemTvWho.setText(CharacterUtil.timeFormat(mDatas.get(page).getResults().get(positionInPage).getPublishedAt()));
            Log.e(TAG, "ewho : " + CharacterUtil.timeFormat(mDatas.get(page).getResults().get(positionInPage).getPublishedAt()));
        }

        holder.itemLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, mDatas.get(page).getResults().get(positionInPage).getUrl());
                Uri uri = Uri.parse(mDatas.get(page).getResults().get(positionInPage).getUrl());
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                mContext.startActivity(intent);
                Intent intent = new Intent(mContext, ShowDetailActivity.class);
                intent.putExtra("TYPE", mDatas.get(page).getResults().get(positionInPage).getType());
                intent.putExtra("DESC", mDatas.get(page).getResults().get(positionInPage).getDesc());
                intent.putExtra("URL", mDatas.get(page).getResults().get(positionInPage).getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size() * VideoFragment.DEFAULT_VIDEO_ITEM_COUNT;
    }

    /*
     * 清除数据
     */
    public void clearDatas() {
        if (mDatas.size() > 0) {
            mDatas.clear();
            notifyDataSetChanged();
        }
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTvDesc;
        public TextView itemTvWho;
        public LinearLayout itemLl;

        public VideoViewHolder(View itemView) {
            super(itemView);
            itemLl = (LinearLayout) itemView.findViewById(R.id.item_ll_video);
            itemTvDesc = (TextView) itemView.findViewById(R.id.item_tv_desc_video);
            itemTvWho = (TextView) itemView.findViewById(R.id.item_tv_who_video);
        }
    }

}
