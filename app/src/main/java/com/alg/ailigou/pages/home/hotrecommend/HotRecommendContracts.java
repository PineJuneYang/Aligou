package com.alg.ailigou.pages.home.hotrecommend;

import com.alg.ailigou.common.model.HotRecommendDataModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.ligoupaper
 * Created by Chris Chen on 2017/7/13 13:47.
 * Explain:利购券
 */

public interface HotRecommendContracts {
    interface View extends BaseView {
        void notify(HotRecommendDataModel model);

        /**
         * 刷新完成
         */
        void refreshComplete();
    }

    interface Presenter extends BasePresenter<View> {
        void loadData(int page, int pageSize);
    }
}
