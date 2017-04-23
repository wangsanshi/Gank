package com.wangsanshi.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.wangsanshi.gank.R;
import com.wangsanshi.gank.entity.VideoBean;
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
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        int page = position / 10;
        int positionOfPage = position % 10;

        holder.itemTvDesc.setText(mDatas.get(page).getResults().get(positionOfPage).getDesc());
        Log.e(TAG, "desc : " + mDatas.get(page).getResults().get(positionOfPage).getDesc());
        if (!mDatas.get(page).getResults().get(positionOfPage).getWho().equals("")) {
            holder.itemTvWho.setText(mContext.getString(R.string.item_who_format,
                    mDatas.get(page).getResults().get(positionOfPage).getWho(),
                    CharacterUtil.timeFormat(mDatas.get(page).getResults().get(positionOfPage).getPublishedAt())));
            Log.e(TAG, "qwho : " + mContext.getString(R.string.item_who_format,
                    mDatas.get(page).getResults().get(positionOfPage).getWho(),
                    CharacterUtil.timeFormat(mDatas.get(page).getResults().get(positionOfPage).getPublishedAt())));
        } else {
            holder.itemTvWho.setText(CharacterUtil.timeFormat(mDatas.get(page).getResults().get(positionOfPage).getPublishedAt()));
            Log.e(TAG, "ewho : " + CharacterUtil.timeFormat(mDatas.get(page).getResults().get(positionOfPage).getPublishedAt()));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() * 10;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public VideoView itemVv;
        public TextView itemTvDesc;
        public TextView itemTvWho;

        public VideoViewHolder(View itemView) {
            super(itemView);
            itemVv = (VideoView) itemView.findViewById(R.id.item_vv_video);
            itemTvDesc = (TextView) itemView.findViewById(R.id.item_tv_desc_video);
            itemTvWho = (TextView) itemView.findViewById(R.id.item_tv_who_video);
        }
    }

}
