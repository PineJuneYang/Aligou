package com.alg.ailigou.pages.personal.myorder.fragment;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/9
 * 此类或接口用于
 */

public class MyOrderFragmentPresenter implements MyOrderFragmentContracts.Presenter {


    private MyOrderFragmentContracts.View view;

    public int allOrderPage = 1, allOrderPageSize = 20;
    public int prePaymentPage = 1, prePaymentPageSize = 20;
    public int preSendPage = 1, preSendPageSize = 20;
    public int preReceivePage = 1, preReceivePageSize = 20;
    public int preCommentPage = 1, preCommentPageSize = 20;
//    type:0表示全部订单   1:待付款   2:待发货  3:代收货
//     4:待评价

    @Inject
    public MyOrderFragmentPresenter() {
    }

    @Override
    public void bindView(MyOrderFragmentContracts.View view) {
        this.view = view;
    }


    @Override
    public void unbindView() {
        this.view = null;
    }


    @Override
    public void loadAll() {

        UserApi.getOrderDetailsList(0, allOrderPage, allOrderPageSize, new NetCallback<PageModel<OrderDetailsDataModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<OrderDetailsDataModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notifyAllOrder(netResponse.data);
                }
            }
        });


    }

    @Override
    public void loadPrePayment() {
        UserApi.getOrderDetailsList(1, allOrderPage, allOrderPageSize, new NetCallback<PageModel<OrderDetailsDataModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<OrderDetailsDataModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notifyPrePayment(netResponse.data);
                }
            }
        });
    }

    @Override
    public void loadPreSend() {
        UserApi.getOrderDetailsList(2, allOrderPage, allOrderPageSize, new NetCallback<PageModel<OrderDetailsDataModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<OrderDetailsDataModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notifyPreSend(netResponse.data);
                }
            }
        });
    }

    @Override
    public void loadPreReceive() {
        UserApi.getOrderDetailsList(3, allOrderPage, allOrderPageSize, new NetCallback<PageModel<OrderDetailsDataModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<OrderDetailsDataModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notifyPreReceive(netResponse.data);
                }
            }
        });

    }

    @Override
    public void loadPreCommentt() {
        UserApi.getOrderDetailsList(4, allOrderPage, allOrderPageSize, new NetCallback<PageModel<OrderDetailsDataModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<OrderDetailsDataModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notifyPreComment(netResponse.data);
                }
            }
        });

    }

    @Override
    public void cancelOrder(long orderId, final int position) {
        UserApi.deleteOrder(orderId, new NetCallback<Object>() {

            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess) {
                    // TODO: 2017/9/8  已经成功取消了订单,应该在集合中删除该元素
                    view.cancelOrderNotify(position);
                }
            }
        });
    }
}
