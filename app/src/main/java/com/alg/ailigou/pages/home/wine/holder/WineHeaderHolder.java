package com.alg.ailigou.pages.home.wine.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.widget.banner.BannerViewPager;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/18.
 * 此类或接口用于  佳酿的头部holder
 */

public class WineHeaderHolder extends MyBaseViewHolder {
    @BindView(R.id.bannerview)
    BannerViewPager bannerview;
    @BindView(R.id.iv_home_wine_detail)
    ImageView ivHomeWineDetail;
    @BindView(R.id.tv_home_wine_banner_desc)
    TextView tvHomeWineBannerDesc;
    public WineHeaderHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);

    }
}
