package com.alg.ailigou.pages.home.algchoice.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/20.
 * 此类或接口用于
 */

public class AlgChoiceNormalHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_brand_name)
    TextView mTvBrandName;
    @BindView(R.id.tv_plan)
    TextView mTvPlan;
    @BindView(R.id.tv_time)
    TextView mTvTime;

    public ImageView getIvGoods() {
        return mIvGoods;
    }

    public void setIvGoods(ImageView ivGoods) {
        mIvGoods = ivGoods;
    }

    public TextView getTvBrandName() {
        return mTvBrandName;
    }

    public void setTvBrandName(TextView tvBrandName) {
        mTvBrandName = tvBrandName;
    }

    public TextView getTvPlan() {
        return mTvPlan;
    }

    public void setTvPlan(TextView tvPlan) {
        mTvPlan = tvPlan;
    }

    public TextView getTvTime() {
        return mTvTime;
    }

    public void setTvTime(TextView tvTime) {
        mTvTime = tvTime;
    }

    public AlgChoiceNormalHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
}
