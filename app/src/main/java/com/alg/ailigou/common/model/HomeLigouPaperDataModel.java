package com.alg.ailigou.common.model;

/**
 * Created by 海航
 * on 2017/7/25.
 * 此类或接口用于  利购券商城model
 */

public class HomeLigouPaperDataModel {
    public BannerModel banner;//轮播图
    public int overage;//利购券余额
    public int exChangeCount;//兑换记录
    public PageModel<CommodityModel> mCommodityModelPageModel;
}
