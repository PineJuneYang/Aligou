package com.alg.ailigou.pages.mall.orderdetails;

import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 海航
 * on 2017/8/1.
 * 此类或接口用于
 */

public class OrderDetailsContracts {
    interface View extends BaseView {

        void updataViews(OrderDetailsDataModel dataModel);

    }


    interface Presenter extends BasePresenter<View> {

        void loadData(long id);

    }
}
