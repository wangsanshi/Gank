package com.wangsanshi.gank.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangsanshi.gank.R;
import com.wangsanshi.gank.entity.CollectionBean;
import com.wangsanshi.gank.entity.Constant;
import com.wangsanshi.gank.util.Utility;

import java.util.List;

public class CollectionRvAdapter extends RecyclerView.Adapter<CollectionRvAdapter.CollectionViewHolder>
        implements com.wangsanshi.gank.onItemRemoveListener {
    private List<CollectionBean> mDatas;
    private LayoutInflater mLnflater;
    private Context mContext;

    public CollectionRvAdapter(Context context, List<CollectionBean> datas) {
        this.mDatas = datas;
        mLnflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectionViewHolder(mLnflater.inflate(R.layout.item_rv_collection, parent, false));
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        holder.itemTvType.setText(mDatas.get(position).getType());
        holder.itemTvDate.setText(mDatas.get(position).getDate());
        holder.itemTVTitle.setText(mDatas.get(position).getTitle());
        holder.itemTvUrl.setText(mDatas.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onItemRemove(int position) {
        Utility.removeCollectionMsg(mContext.getSharedPreferences(Constant.COLLECTION_SPF_NAME, Context.MODE_PRIVATE),
                mDatas.get(position).getId());
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public class CollectionViewHolder extends RecyclerView.ViewHolder {
        public CardView itemCardView;
        public TextView itemTvType;
        public TextView itemTvDate;
        public TextView itemTVTitle;
        public TextView itemTvUrl;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            itemCardView = (CardView) itemView;
            itemTvType = (TextView) itemView.findViewById(R.id.item_tv_type_collection);
            itemTvDate = (TextView) itemView.findViewById(R.id.item_tv_date_collection);
            itemTVTitle = (TextView) itemView.findViewById(R.id.item_tv_title_collection);
            itemTvUrl = (TextView) itemView.findViewById(R.id.item_tv_url_collection);
        }
    }

}
