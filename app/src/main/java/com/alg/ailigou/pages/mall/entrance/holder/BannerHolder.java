package com.alg.ailigou.pages.mall.entrance.holder;

import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;
import com.alg.ailigou.library.widget.banner.BannerViewPager;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.entrance.holder
 * Created by Chris Chen on 2017/7/13 15:08.
 * Explain:todo 测试 头部轮播图
 */

public class BannerHolder extends BaseRecyclerHolder{
    @BindView(R.id.bvp_home_mall_top)
    public BannerViewPager banner;

    public BannerHolder(View itemView) {
        super(itemView);
    }
}
