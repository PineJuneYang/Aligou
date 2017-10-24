package com.alg.ailigou.pages.personal.myorder.fragment;

import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/9
 * 此类或接口用于
 */

public interface MyOrderFragmentContracts {

    interface View extends BaseView{

        void notifyAllOrder(PageModel<OrderDetailsDataModel> data);

        void notifyPrePayment(PageModel<OrderDetailsDataModel> data);

        void notifyPreSend(PageModel<OrderDetailsDataModel> data);

        void notifyPreReceive(PageModel<OrderDetailsDataModel> data);

        void notifyPreComment(PageModel<OrderDetailsDataModel> data);

        /**
         * 取消订单成功后 更新recylerview
         * @param position
         */
        void cancelOrderNotify(int position);
    }

    interface Presenter extends BasePresenter<View>{

        void  loadAll(); //加载全部订单的数据

        void loadPrePayment(); //加载待付款的订单

        void loadPreSend(); //加载待发货的订单

        void loadPreReceive();  //加载待收货的订单

        void loadPreCommentt();  //加载待评价的订单

        void cancelOrder(long orderId,int position);//取消订单
    }
}

