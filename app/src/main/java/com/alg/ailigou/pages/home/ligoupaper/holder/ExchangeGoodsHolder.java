package com.alg.ailigou.pages.home.ligoupaper.holder;

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
 * 此类或接口用于  利购券商城的vh
 */

public class ExchangeGoodsHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_goods_price)
    TextView mTvGoodsPrice;
    @BindView(R.id.tv_cheap_ticket_count)
    TextView mTvCheapTicketCount;
    @BindView(R.id.tv_exchange_now)
    TextView mTvExchangeNow;

    public ExchangeGoodsHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    public ImageView getIvGoods() {
        return mIvGoods;
    }

    public TextView getTvGoodsName() {
        return mTvGoodsName;
    }

    public TextView getTvGoodsPrice() {
        return mTvGoodsPrice;
    }

    public TextView getTvCheapTicketCount() {
        return mTvCheapTicketCount;
    }

    public TextView getTvExchangeNow() {
        return mTvExchangeNow;
    }
}
