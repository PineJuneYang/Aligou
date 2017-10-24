package com.alg.ailigou.pages.main.entrance;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * com.alg.ailigouapp.pages.main.entrance
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 11:59
 * Explain:契约
 */

public interface MainContracts {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 加载数据
         */
        void loadData();
    }
}
