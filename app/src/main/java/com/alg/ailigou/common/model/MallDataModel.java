package com.alg.ailigou.common.model;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.common.model
 * Created by Chris Chen on 2017/7/21 16:39.
 * Explain:商城页面数据
 */

public class MallDataModel {
    public List<BannerModel> scrollBannerData;//轮播广告数据
    public String fixBannerUrl;//固定广告图片url
    public List<HomeCommodityTypeModel> hotBrandList;//热销品牌
    public List<CommodityModel> popularityList;//人气推荐
}
