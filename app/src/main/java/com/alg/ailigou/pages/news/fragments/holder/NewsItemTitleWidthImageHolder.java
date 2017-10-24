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
 * on 2017/7/19.
 * 此类或接口用于 新闻列表的item  上面是标题下面是一张图片
 */

public class NewsItemTitleWidthImageHolder extends MyBaseViewHolder {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    public NewsItemTitleWidthImageHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        mTvTitle = tvTitle;
    }

    public ImageView getIvGoods() {
        return mIvGoods;
    }

    public void setIvGoods(ImageView ivGoods) {
        mIvGoods = ivGoods;
    }
}
