package com.alg.ailigou.pages.home.newgoods;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.newsgoods
 * Created by Chris Chen on 2017/7/13 13:51.
 * Explain:新品
 */

public interface NewGoodsContracts {
    interface View extends BaseView {
        void notifity(PageModel<CommodityModel> pageModel);
    }

    interface Presenter extends BasePresenter<View> {
        void loadData(int page, int pageSize);
    }
}
