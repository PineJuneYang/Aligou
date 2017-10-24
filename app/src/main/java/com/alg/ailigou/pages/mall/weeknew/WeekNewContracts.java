package com.alg.ailigou.pages.mall.weeknew;

import com.alg.ailigou.common.model.WeekNewGoodsDataModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.weeknew
 * Created by Chris Chen on 2017/7/21 16:10.
 * Explain:每周上新
 */

public class WeekNewContracts {
    interface View extends BaseView {

        void updateWeekNewActivity(WeekNewGoodsDataModel weekNewGoodsDataModel);

    }

    interface Presenter extends BasePresenter<View> {
        void loadData();
    }
}
