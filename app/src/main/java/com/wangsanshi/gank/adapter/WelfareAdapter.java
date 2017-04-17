package com.wangsanshi.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wangsanshi.gank.R;
import com.wangsanshi.gank.entity.WelfareBean;

import java.util.List;

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.WelfareViewHolder> {
    private Context mContext;
    private List<WelfareBean> mDatas;

    public WelfareAdapter(Context context, List<WelfareBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public WelfareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WelfareViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_rv_welfare, parent, false));
    }

    @Override
    public void onBindViewHolder(WelfareViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mDatas.get(position / 10).getResults().get(position % 10).getUrl())
                .into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return mDatas.size() * 10;
    }

    public class WelfareViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;

        public WelfareViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView;
        }
    }
}
