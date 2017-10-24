package com.alg.ailigou.pages.home.wine.holder;

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
 * 此类或接口用于 横行酒的系列recylerview
 */

public class WineHorizontalHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;

    public ImageView getIvGoods() {
        return mIvGoods;
    }

    public TextView getTvGoodsName() {
        return mTvGoodsName;
    }

    public WineHorizontalHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

}
