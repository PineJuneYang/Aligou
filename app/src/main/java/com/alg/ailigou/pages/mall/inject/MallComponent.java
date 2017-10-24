package com.alg.ailigou.pages.mall.inject;

import com.alg.ailigou.common.inject.ActivityModule;
import com.alg.ailigou.pages.mall.advice.AdviceActivity;
import com.alg.ailigou.pages.mall.brand.BrandActivity;
import com.alg.ailigou.pages.mall.details.CommodityDetailsActivity;
import com.alg.ailigou.pages.mall.entrance.MallActivity;
import com.alg.ailigou.pages.mall.goodslist.GoodsListActivity;
import com.alg.ailigou.pages.mall.goodslist.fragment.GoodsListFragment;
import com.alg.ailigou.pages.mall.limit.LimitActivity;
import com.alg.ailigou.pages.mall.orderdetails.OrderDetailsActivity;
import com.alg.ailigou.pages.mall.pay.PayActivity;
import com.alg.ailigou.pages.mall.weeknew.WeekNewActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.inject
 * Created by Chris Chen on 2017/7/21 15:22.
 * Explain:商城Dagger2注入器
 */
@Singleton
@Component(modules = ActivityModule.class)
public interface MallComponent {
    void inject(MallActivity mallActivity);//商城

    void inject(CommodityDetailsActivity commodityDetailsActivity);//商品详情

    void inject(BrandActivity brandActivity);//某品牌

    void inject(LimitActivity limitActivity);//限时特购

    void inject(PayActivity payActivity);//支付

    void inject(AdviceActivity adviceActivity);//咨询

    void inject(WeekNewActivity weekNewActivity);//每周上新

    void inject(GoodsListActivity goodsListActivity); //商品列表

    void inject(GoodsListFragment goodsListFragment);  //商品列表里的fragment

    void inject(OrderDetailsActivity orderDetailsActivity);  //订单详情


}
