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

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.WelfareViewHolder> {
    private Context mContext;
    private WelfareBean mData;

    public WelfareAdapter(Context context, WelfareBean data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public WelfareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WelfareViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_rv_welfare, parent, false));
    }

    @Override
    public void onBindViewHolder(WelfareViewHolder holder, int position) {
        Glide.with(mContext).load(mData.getResults().get(position).getUrl()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return mData.getResults().size();
    }

    public class WelfareViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;

        public WelfareViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView;
        }
    }
}
