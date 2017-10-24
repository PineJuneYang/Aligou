package com.alg.ailigou.pages.home.ligoupaper;

import com.alg.ailigou.common.model.HomeLigouPaperDataModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.ligoupaper
 * Created by Chris Chen on 2017/7/13 13:47.
 * Explain:利购券
 */

public interface LigouPaperContracts {
    interface View extends BaseView {

        void notify(HomeLigouPaperDataModel dataModel);
    }

    interface Presenter extends BasePresenter<View> {
        void loadData(int pager, int pagerSize);
    }
}
