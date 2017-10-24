package com.alg.ailigou.pages.personal.commonorderdetails;

import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public interface CommonOrderDetailsContracts {
    interface View extends BaseView {
        /**
         * 拿到详情信息,填充数据
         *
         * @param object
         */
        void setOrderData(OrderDetailsDataModel object);

        void showDialog();

        void dismissDialog();


    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 获取订单详情
         *
         * @param orderId
         */
        void loadGoodDetails(long orderId);

        /**
         * 确认收货
         *
         * @param orderId
         */
        void receiveGoods(long orderId);

        /**
         * 取消订单
         *
         * @param orderId
         */
        void cancelOrder(long orderId);
    }
}
