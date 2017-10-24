package com.alg.ailigou.pages.home.everydaycheap;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.ligoupaper
 * Created by Chris Chen on 2017/7/13 13:47.
 * Explain:利购券
 */

public interface EveryDayCheapContracts {
    interface View extends BaseView {
        void notifity(List<CommodityModel> modelList);
    }

    interface Presenter extends BasePresenter<View> {
        void loadData();
    }
}
