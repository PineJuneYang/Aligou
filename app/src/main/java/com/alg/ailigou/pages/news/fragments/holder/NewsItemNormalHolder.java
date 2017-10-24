package com.alg.ailigou.pages.news.fragments.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/13.
 * 此类或接口用于  新闻条目的holder
 */

public class NewsItemNormalHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_news)
    ImageView mIvNews;
    @BindView(R.id.tv_news_desc)
    TextView mTvNewsDesc;
    @BindView(R.id.tv_time)
    TextView mTvTime;

    public ImageView getIvNews() {
        return mIvNews;
    }

    public void setIvNews(ImageView ivNews) {
        mIvNews = ivNews;
    }

    public TextView getTvNewsDesc() {
        return mTvNewsDesc;
    }

    public void setTvNewsDesc(TextView tvNewsDesc) {
        mTvNewsDesc = tvNewsDesc;
    }

    public TextView getTvTime() {
        return mTvTime;
    }

    public void setTvTime(TextView tvTime) {
        mTvTime = tvTime;
    }

    public NewsItemNormalHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
}
