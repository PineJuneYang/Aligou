package com.alg.ailigou.pages.personal.myfoot.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/8.
 * 此类或接口用于
 */

public class MyFootNotesHolder extends MyBaseViewHolder {
    @BindView(R.id.tv_title_time)
    TextView mTvTitleTime;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_goods_now_price)
    TextView mTvGoodsNowPrice;
    @BindView(R.id.tv_goods_old_price)
    TextView mTvGoodsOldPrice;
    @BindView(R.id.tv_goods)
    ImageView mIvGoods;
    @BindView(R.id.ll_select)
    LinearLayout mLlSelect;
    @BindView(R.id.iv_select)
    ImageView mIvSelect;

    public LinearLayout getLliSelect() {
        return mLlSelect;
    }

    public ImageView getIvSelect() {
        return mIvSelect;
    }

    public ImageView getIvGoods() {
        return mIvGoods;
    }

    public TextView getTvTitleTime() {
        return mTvTitleTime;
    }

    public TextView getTvGoodsName() {
        return mTvGoodsName;
    }

    public TextView getTvGoodsNowPrice() {
        return mTvGoodsNowPrice;
    }

    public TextView getTvGoodsOldPrice() {
        return mTvGoodsOldPrice;
    }

    public MyFootNotesHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    OnClickListener onClickListener;

    public MyFootNotesHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener) {
        super(itemView, listener);
        this.onClickListener = onClickListener;
    }

    @OnClick(R.id.ll_select)
    public void onViewClicked(View view) {
        if (onClickListener != null) {
            onClickListener.setOnClickListener(view, getAdapterPosition());
        }
    }
}
