package com.alg.ailigou.common.model;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于 爆款特价
 */

public class HotRecommendDataModel {
    public BannerModel mBannerModel;
    public List<CommodityModel> cheapGoods;//爆款特价  只有两个
    public PageModel<CommodityModel> recommendGoods;//下面列表
}
