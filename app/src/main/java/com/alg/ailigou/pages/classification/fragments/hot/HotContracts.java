package com.alg.ailigou.pages.classification.fragments.hot;

import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.classification.fragments.hot
 * Created by Chris Chen on 2017/7/11 13:07.
 * Explain:热卖推荐
 */

public interface HotContracts {
    interface View extends BaseView {
        void updateCommodityBannerData(List<BannerModel> bannerModelList);//轮播广告

        void updateNewCommodityData(List<CommodityTypeModel> goodsTypeList);//新品专场

        void updateGoodCommodityData(List<CommodityTypeModel> goodsTypeList);//好货榜单

//        void updateFashionCommodityData(List<CommodityTypeModel> goodsTypeList);//时尚奢品
    }

    interface Presenter extends BasePresenter<View> {
        void loadCommodityBannerData();//轮播广告

        void loadNewCommodityData();//新品专场

        void loadGoodCommodityData();//好货榜单

//        void loadFashionCommodityData();//时尚奢品
    }
}
