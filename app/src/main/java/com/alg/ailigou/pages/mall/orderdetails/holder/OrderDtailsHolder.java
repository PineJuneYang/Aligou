package com.alg.ailigou.pages.mall.orderdetails.holder;

import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/2.
 * 此类或接口用于 OrderDtailsAdpter 的holder
 *
 */

public class OrderDtailsHolder extends MyBaseViewHolder {
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_goods_desc)
    TextView mTvGoodsDesc;
    @BindView(R.id.tv_goods_count)
    TextView mTvGoodsCount;

    @BindView(R.id.tv_edit_goods_count)
    TextView mTvEditGoodsCount;
    @BindView(R.id.tv_goods_price)
    TextView mTvGoodsPrice;
    @BindView(R.id.tv_cart_delete)
    TextView mTvCartDelete;
    @BindView(R.id.tv_cart_add)
    TextView mTvCartAdd;
    private OnClickListener onClickListener;

    public TextView getTvGoodsName() {
        return mTvGoodsName;
    }

    public void setTvGoodsName(TextView tvGoodsName) {
        mTvGoodsName = tvGoodsName;
    }

    public TextView getTvGoodsDesc() {
        return mTvGoodsDesc;
    }

    public void setTvGoodsDesc(TextView tvGoodsDesc) {
        mTvGoodsDesc = tvGoodsDesc;
    }

    public TextView getTvGoodsCount() {
        return mTvGoodsCount;
    }

    public void setTvGoodsCount(TextView tvGoodsCount) {
        mTvGoodsCount = tvGoodsCount;
    }

    public TextView getTvGoodsPrice() {
        return mTvGoodsPrice;
    }

    public void setTvGoodsPrice(TextView tvGoodsPrice) {
        mTvGoodsPrice = tvGoodsPrice;
    }

    public TextView getTvCartDelete() {
        return mTvCartDelete;
    }

    public void setTvCartDelete(TextView tvCartDelete) {
        mTvCartDelete = tvCartDelete;
    }

    public TextView getTvCartAdd() {
        return mTvCartAdd;
    }

    public void setTvCartAdd(TextView tvCartAdd) {
        mTvCartAdd = tvCartAdd;
    }

    public OrderDtailsHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    public TextView getTvEditGoodsCount() {
        return mTvEditGoodsCount;
    }

    public OrderDtailsHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener) {
        super(itemView, listener);
        this.onClickListener = onClickListener;
    }

    @OnClick({R.id.tv_cart_delete, R.id.tv_cart_add})
    public void onViewClicked(View view) {
        onClickListener.setOnClickListener(view,getAdapterPosition());

    }
}
