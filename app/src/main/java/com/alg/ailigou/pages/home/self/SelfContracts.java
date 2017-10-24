package com.alg.ailigou.pages.home.self;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.SelfGoodsDataModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.self
 * Created by Chris Chen on 2017/7/13 13:55.
 * Explain:自营
 */

public interface SelfContracts {
    interface View extends BaseView {
        //        void updateBannerData(List<BannerModel> bannerModelList);
//
//        void updateClassicData(List<HomeCommodityTypeModel> commodityTypeModelList);
//
//        void updateHotData(List<CommodityModel> commodityModelList);
//
//        void updateMonthActionData(List<CommodityModel> commodityModelList);
        void undateSelfData(SelfGoodsDataModel model);

        void undateCommendData(PageModel<CommodityModel> commodityModelPageModel);
    }

    interface Presenter extends BasePresenter<View> {
//        void loadBannerData();
//
//        void loadClassicData();
//
//        void loadHotData();
//
//        void loadMonthActionData();

        void loadSelfData();

        void loadCommendData(int type,int page, int pageSize);
    }
}
