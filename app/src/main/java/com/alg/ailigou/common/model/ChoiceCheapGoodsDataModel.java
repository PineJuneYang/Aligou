package com.alg.ailigou.common.model;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于  精选特价
 */

public class ChoiceCheapGoodsDataModel {
    public long intoTime;//入场时间
    public long  nowTime;//当前时间
    public PageModel<CommodityModel> goods;
    public BannerModel mBannerModel;
}
