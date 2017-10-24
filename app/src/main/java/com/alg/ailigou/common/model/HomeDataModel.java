package com.alg.ailigou.common.model;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/7/19 13:19.
 * Explain:Home页面数据
 */

public class HomeDataModel {
    public List<BannerModel> newsBannerData;//头部新闻轮播广告
    public PageModel<NewsModel> newsList;//爱利购快讯
    public LimitBlockModel limitBlockData;//限时抢购数据
    public String luckUrl;//幸运大转盘图片url
    public List<BannerModel> actionBannerData;//活动轮播广告
    public List<CommodityModel> boutiqueList;//精品推荐商品列表 10条 单条数据包含：id、图片、标题
    public BannerModel unionBanner;//联盟商家轮播广告,只有一张
    public PageModel<UnionModel> businessList;//商户列表
}
