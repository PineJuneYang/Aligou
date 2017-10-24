package com.alg.ailigou.pages.personal.commonorderdetails;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/8/4.
 * 此类或接口用于
 */

public class CommonOrderDetailsPresenter implements CommonOrderDetailsContracts.Presenter {
    CommonOrderDetailsContracts.View view;

    @Inject
    public CommonOrderDetailsPresenter() {
    }

    @Override
    public void bindView(CommonOrderDetailsContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadGoodDetails(long orderId) {
        HomeApi.getOrderDetailsData(1, new NetCallback<OrderDetailsDataModel>() {
            @Override
            protected void onComplete(NetResponse<OrderDetailsDataModel> netResponse) {
                long times = netResponse.data.orderTime;
                view.setOrderData(netResponse.data);

            }
        });
    }

    @Override
    public void receiveGoods(long orderId) {

    }

    @Override
    public void cancelOrder(long orderId) {

    }
}
