package com.alg.ailigou.pages.home.inject;


import com.alg.ailigou.pages.home.algchoice.AlgChoiceActivity;
import com.alg.ailigou.pages.home.entrance.HomeFragment;
import com.alg.ailigou.pages.home.everydaycheap.EveryDayCheapActivity;
import com.alg.ailigou.pages.home.goodslist.GoodsListActivity;
import com.alg.ailigou.pages.home.goodslist.fragment.GoodsListFragment;
import com.alg.ailigou.pages.home.hotrecommend.HotRecommendActivity;
import com.alg.ailigou.pages.home.hotsalelist.HotSaleListActivity;
import com.alg.ailigou.pages.home.hotsalelist.fragment.HotSaleListFragment;
import com.alg.ailigou.pages.home.hotsaleweek.HotSaleWeekActivity;
import com.alg.ailigou.pages.home.hotsaleweek.fragment.HotSaleWeekFragment;
import com.alg.ailigou.pages.home.ligouchangedetails.LigouChangeDetailsActivity;
import com.alg.ailigou.pages.home.ligouchangenotes.LigouChangeNotesActivity;
import com.alg.ailigou.pages.home.ligouoverage.LigouOverageActivity;
import com.alg.ailigou.pages.home.ligoupaper.LigouPaperActivity;
import com.alg.ailigou.pages.home.luxurybrand.LuxuryBrandActivity;
import com.alg.ailigou.pages.home.make.BillDetailActivity;
import com.alg.ailigou.pages.home.newgoods.NewGoodsActivity;
import com.alg.ailigou.pages.home.search.SearchDetailActivity;
import com.alg.ailigou.pages.home.search.fragment.SearchDetailFragment;
import com.alg.ailigou.pages.home.searchgoods.SearchGoodsActivity;
import com.alg.ailigou.pages.home.self.SelfActivity;
import com.alg.ailigou.pages.home.wine.WineActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.inject
 * Created by Chris Chen on 2017/7/4 16:02.
 * Explain:
 */
@Singleton
@Component(modules = {HomeModule.class})
public interface HomeComponent {

    void inject(HomeFragment homeFragment);//入口

    void inject(SearchGoodsActivity searchGoodsActivity);//搜索商品

    void inject(SelfActivity selfActivity);//自营

    void inject(LigouPaperActivity ligouPaperActivity);//利购券

    void inject(NewGoodsActivity newGoodsActivity);//新品

    void inject(WineActivity wineActivity);//佳酿

    void inject(EveryDayCheapActivity everyDayCheapActivity);//天天特价

    void inject(AlgChoiceActivity algChoiceActivity);//爱利购精选

    void inject(HotSaleWeekActivity hotSaleWeekActivity);//每周热卖

    void inject(LuxuryBrandActivity luxuryBrandActivity);//奢侈品牌

    void inject(HotRecommendActivity hotRecommendActivity);//爆款推荐

    void inject(HotSaleListActivity hotSaleListActivity);//热销榜

    void inject(HotSaleListFragment hotSaleListFragment); //热销榜里面的fragment

    void inject(HotSaleWeekFragment hotSaleWeekFragment);//每周热卖里面的fragment

    void inject(SearchDetailActivity searchDetailActivity);// 首页搜索商品详情页

    void inject(SearchDetailFragment searchDetailFragment);//搜索商品详情页的fragment


    void inject(GoodsListActivity goodsListActivity);  //home的商品列表activity

    void inject(GoodsListFragment goodsListFragment);   //home 的商品列表的fragment

    void inject(LigouChangeDetailsActivity ligouChangeDetailsActivity);//利购券兑换详情

    void inject(LigouChangeNotesActivity ligouChangeNotesActivity);//利购券兑换记录

    void inject(LigouOverageActivity ligouChangeNotesActivity);//利购券余额

    void inject(BillDetailActivity billDetailActivity); //做单里面的账单详情
}
