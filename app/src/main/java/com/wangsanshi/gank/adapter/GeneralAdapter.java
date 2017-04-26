package com.wangsanshi.gank.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangsanshi.gank.R;
import com.wangsanshi.gank.activity.ShowDetailActivity;
import com.wangsanshi.gank.entity.GeneralBean;
import com.wangsanshi.gank.util.CharacterUtil;

import java.util.List;

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.GenneralViewHolder> {
    public static final int DEFAULT_ITEM_COUNT = 5;

    private LayoutInflater inflater;
    private Context mContext;
    private List<GeneralBean> mDatas;

    public GeneralAdapter(Context context,List<GeneralBean> datas) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.mDatas = datas;
    }

    @Override
    public GenneralViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenneralViewHolder(inflater.inflate(R.layout.item_rv_general,parent,false));
    }

    @Override
    public void onBindViewHolder(GenneralViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final int page = position / DEFAULT_ITEM_COUNT;
        final int positionInPage = position % DEFAULT_ITEM_COUNT;

        holder.itemTvDesc.setText(mDatas.get(page).getResults().get(positionInPage).getDesc());
        if (mDatas.get(page).getResults().get(positionInPage).getWho() != null) {
            holder.itemTvWho.setText(mContext.getString(R.string.item_who_format,
                    mDatas.get(page).getResults().get(positionInPage).getWho(),
                    CharacterUtil.timeFormat(mDatas.get(page).getResults().get(positionInPage).getPublishedAt())));
        } else {
            holder.itemTvWho.setText(CharacterUtil.timeFormat(mDatas.get(page).getResults().get(positionInPage).getPublishedAt()));
        }

        holder.itemLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowDetailActivity.class);
                intent.putExtra("TYPE", mDatas.get(page).getResults().get(positionInPage).getType());
                intent.putExtra("DESC", mDatas.get(page).getResults().get(positionInPage).getDesc());
                intent.putExtra("URL", mDatas.get(page).getResults().get(positionInPage).getUrl());
                mContext.startActivity(intent);
                Activity activity = (Activity) mContext;
                activity.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size() * DEFAULT_ITEM_COUNT;
    }

    public class GenneralViewHolder extends RecyclerView.ViewHolder{
        public TextView itemTvDesc;
        public TextView itemTvWho;
        public LinearLayout itemLl;

        public GenneralViewHolder(View itemView) {
            super(itemView);
            itemTvDesc =(TextView) itemView.findViewById(R.id.item_tv_desc_general);
            itemTvWho = (TextView)itemView.findViewById(R.id.item_tv_who_general);
            itemLl = (LinearLayout) itemView.findViewById(R.id.item_ll_general);
        }
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
}
