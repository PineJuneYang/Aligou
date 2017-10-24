package com.alg.ailigou.common.model;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/19.
 * 此类或接口用于  佳酿数据
 */

public class HomeWineDataModel {
    public long time;//时间
    public List<CommodityModel> wineBannerList;//佳酿最上面商品的banner
    public List<CommodityTypeModel> wineSeriesList;//酒的系列列表
    public List<BannerModel> sportBannerList;//活动banner
}
