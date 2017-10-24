package com.alg.ailigou.pages.mall.entrance;

import com.alg.ailigou.common.model.MallDataModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.entrance
 * Created by Chris Chen on 2017/7/13 13:31.
 * Explain: 商城
 */

public interface MallContracts {
    interface View extends BaseView {
        void updateData(MallDataModel mallDataModel);//更新页面数据
    }

    interface Presenter extends BasePresenter<View> {
        void loadData();//加载页面数据
    }
}
