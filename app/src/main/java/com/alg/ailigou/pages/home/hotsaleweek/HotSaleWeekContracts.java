package com.alg.ailigou.pages.home.hotsaleweek;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.ligoupaper
 * Created by Chris Chen on 2017/7/13 13:47.
 * Explain:利购券
 */

public interface HotSaleWeekContracts {
    interface View extends BaseView{
        void   updateTopImageViewData(HomeHotSaleCommodityModel homeHotSaleCommodityModelPageModel) ; ///更新活动banner数据
        void updateTabLayoutTitle(List<String> titles);
    }
    interface Presenter extends BasePresenter<View>{
        void loadActionBannerData();
        void loadTabLayoutTitle();
    }
}
