package com.alg.ailigou.pages.personal.seelogistics.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/8/9.
 * 此类或接口用于
 */

public class OrderGoodsHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_goods_desc)
    TextView mTvGoodsDesc;
    @BindView(R.id.tv_goods_price)
    TextView mTvGoodsPrice;
    @BindView(R.id.tv_goods_count)
    TextView mTvGoodsCount;

    public TextView getTvLigouCount() {
        return mTvLigouCount;
    }

    @BindView(R.id.tv_ligou_count)
    TextView mTvLigouCount;

    public ImageView getIvGoods() {
        return mIvGoods;
    }

    public TextView getTvGoodsName() {
        return mTvGoodsName;
    }

    public TextView getTvGoodsDesc() {
        return mTvGoodsDesc;
    }

    public TextView getTvGoodsPrice() {
        return mTvGoodsPrice;
    }

    public TextView getTvGoodsCount() {
        return mTvGoodsCount;
    }

    public OrderGoodsHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
}
