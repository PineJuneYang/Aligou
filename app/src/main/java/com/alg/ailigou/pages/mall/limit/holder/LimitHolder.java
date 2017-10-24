package com.alg.ailigou.pages.mall.limit.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/21.
 * 此类或接口用于
 */

public class LimitHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_goods_title)
    TextView mTvGoodsTitle;
    @BindView(R.id.tv_now_price)
    TextView mTvNowPrice;
    @BindView(R.id.tv_old_price)
    TextView mTvOldPrice;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_rush_to_buy)
    TextView mTvRushToBuy;
    OnClickListener onClickListener;
    private Context mContext;




    public ImageView getIvGoods() {
        return mIvGoods;
    }

    public TextView getTvGoodsTitle() {
        return mTvGoodsTitle;
    }

    public TextView getTvNowPrice() {
        return mTvNowPrice;
    }

    public TextView getTvOldPrice() {
        return mTvOldPrice;
    }

    public TextView getTvTime() {
        return mTvTime;
    }

    public TextView getTvRushToBuy() {
        return mTvRushToBuy;
    }

    public LimitHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);

    }

    public LimitHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener, Context context) {
        super(itemView, listener);
        this.mContext = context;
    }

    @OnClick(R.id.tv_rush_to_buy)
    public void onViewClicked(View view) {
        if (onClickListener != null) {
            onClickListener.setOnClickListener(view, getAdapterPosition());
        }
    }


}
