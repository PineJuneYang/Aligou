package com.alg.ailigou.pages.cart.entrance.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/12.
 * 此类或接口用于  商品推荐条目的viewholder
 */

public class GoodsRecommendViewHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_goods_info)
    ImageView mIvGoodsInfo;
    @BindView(R.id.tv_goods_desc)
    TextView mTvGoodsDesc;
    @BindView(R.id.tv_price)
    TextView mTvPrice;

    public ImageView getIvGoodsInfo() {
        return mIvGoodsInfo;
    }

    public void setIvGoodsInfo(ImageView ivGoodsInfo) {
        mIvGoodsInfo = ivGoodsInfo;
    }

    public TextView getTvGoodsDesc() {
        return mTvGoodsDesc;
    }

    public void setTvGoodsDesc(TextView tvGoodsDesc) {
        mTvGoodsDesc = tvGoodsDesc;
    }

    public TextView getTvPrice() {
        return mTvPrice;
    }

    public void setTvPrice(TextView tvPrice) {
        mTvPrice = tvPrice;
    }

    public View getItemView() {
        return itemView;
    }

    public void setItemView(View itemView) {
        this.itemView = itemView;
    }

    private View itemView;

    public GoodsRecommendViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
        this.itemView = itemView;
    }
}
