package com.alg.ailigou.pages.personal.seelogistics;

import com.alg.ailigou.common.model.LogisticsModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public interface SeeLogisticsContracts {
    interface View extends BaseView {
        void goodsListNotify(Object data);

        void logisticsListNotify(LogisticsModel model);
    }

    interface Presenter extends BasePresenter<View> {
        void loadGoodsData(long id);

        /**
         *
         * @param
         */
        void loadLogisticsData(String  shipperCode,String logisticCode);
    }
}
