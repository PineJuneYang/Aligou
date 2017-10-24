package com.alg.ailigou.pages.mall.limit;

import com.alg.ailigou.common.model.ChoiceCheapGoodsDataModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.limit
 * Created by Chris Chen on 2017/7/21 15:55.
 * Explain:限时特购  现在叫做  精选特价
 */

public interface LimitContracts {
    interface View extends BaseView {
        void notify(ChoiceCheapGoodsDataModel model);
        /**
         * 刷新完成
         */
        void refreshComplete();
    }

    interface Presenter extends BasePresenter<View> {
        void LoadData(int page,int pageSize);
    }
}
