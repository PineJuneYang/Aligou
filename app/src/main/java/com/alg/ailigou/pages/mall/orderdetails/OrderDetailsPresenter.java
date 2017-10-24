package com.alg.ailigou.pages.mall.orderdetails;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/8/1.
 * 此类或接口用于
 */

public class OrderDetailsPresenter implements OrderDetailsContracts.Presenter {
    @Inject
    public OrderDetailsPresenter() {
    }

    private OrderDetailsContracts.View view;

    @Override
    public void bindView(OrderDetailsContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData(long id) {
        HomeApi.getOrderDetailsData(id, new NetCallback<OrderDetailsDataModel>() {
            @Override
            protected void onComplete(NetResponse<OrderDetailsDataModel> netResponse) {
                if (netResponse.isSuccess) {
                    view.updataViews(netResponse.data);
                }
            }
        });
    }
}
