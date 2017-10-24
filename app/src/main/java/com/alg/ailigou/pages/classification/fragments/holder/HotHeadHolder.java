package com.alg.ailigou.pages.classification.fragments.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.library.widget.banner.BannerViewPager;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/9/7
 * 此类或接口用于
 */

public class HotHeadHolder extends MyBaseViewHolder {

    @BindView(R.id.bvp_classification_commodity)
    BannerViewPager bvpClassificationCommodity;
    @BindView(R.id.rv_new_commodity_area)
    RecyclerView rvNewCommodityArea;

    public HotHeadHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
}
