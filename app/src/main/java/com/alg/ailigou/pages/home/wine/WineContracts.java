package com.alg.ailigou.pages.home.wine;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeWineDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.wine
 * Created by Chris Chen on 2017/7/13 13:59.
 * Explain:佳酿
 */

public interface WineContracts {
    interface View extends BaseView {
        /**
         * 更新上面的数据
         */
        void wineDataNotifity(HomeWineDataModel model);

        /**
         * 推荐商品刷新
         */
        void recommendGoodsNotifity(PageModel<CommodityModel> bannerModels);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 加载上面的数据
         */
        void loadWineData();

        /**
         * 推荐商品数据
         */
        void loadRecommendGoodsData(int type,int page,int pageSize);
    }
}
