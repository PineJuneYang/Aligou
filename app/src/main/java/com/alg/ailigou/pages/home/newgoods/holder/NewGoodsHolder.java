package com.alg.ailigou.pages.home.newgoods.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/17.
 * 此类或接口用于
 */

public class NewGoodsHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_new)
    TextView mTvNew;
    @BindView(R.id.tv_goods_title)
    TextView mTvGoodsTitle;
    @BindView(R.id.tv_goods_price)
    TextView mTvGoodsPrice;

    public NewGoodsHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    public ImageView getIvGoods() {
        return mIvGoods;
    }

    public TextView getTvNew() {
        return mTvNew;
    }

    public TextView getTvGoodsTitle() {
        return mTvGoodsTitle;
    }

    public TextView getTvGoodsPrice() {
        return mTvGoodsPrice;
    }
}
