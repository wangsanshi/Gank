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
    private OnItemClickListener onItemClickListener;

    private Context mContext;
    private List<WelfareBean> mDatas;
    private LayoutInflater mInflater;

    public WelfareAdapter(Context context, List<WelfareBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public WelfareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WelfareViewHolder(mInflater.inflate(R.layout.item_rv_welfare, parent, false));
    }

    @Override
    public void onBindViewHolder(final WelfareViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        Glide.with(mContext)
                .load(mDatas.get(position / 10).getResults().get(position % 10).getUrl())
                .dontAnimate()
                .into(holder.imageView);

        if (onItemClickListener != null) {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.imageView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() * 10;
    }

    public class WelfareViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public WelfareViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
