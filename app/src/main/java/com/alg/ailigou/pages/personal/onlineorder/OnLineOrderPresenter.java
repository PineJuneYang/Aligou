package com.alg.ailigou.pages.personal.onlineorder;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/16
 * 此类或接口用于
 */

public class OnLineOrderPresenter implements OnLineOrderContracts.Presenter {
    private OnLineOrderContracts.View view;


    public int page = 1;
    public int pageSize = 20;

    @Inject
    public OnLineOrderPresenter() {
    }

    @Override
    public void bindView(OnLineOrderContracts.View view) {
        this.view = view;

    }

    @Override
    public void unbindView() {
        this.view = null;
    }


    @Override
    public void queryOnlineOrder(int page, int pageSize, long beginTime, long endTime, String status) {
        NetCallback<PageModel<OrderDetailsDataModel>> callback = new NetCallback<PageModel<OrderDetailsDataModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<OrderDetailsDataModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notify(netResponse.data);
                }
            }
        };

        UserApi.queryOnlineOrder(page, pageSize, beginTime, endTime, status, callback);

    }

    //    type:0表示全部订单   1:待付款   2:待发货  3:代收货
//     4:待评价  5,已完成,6,退货
    ArrayList list = new ArrayList();
    @Override
    public List<String> getTypes() {
         if (list.size()==0){
             list.add("全部订单");
             list.add("待付款");
             list.add("待发货");
             list.add("代收货");
             list.add("待评价");
             list.add("已完成");
             list.add("退货");
         }
        return list;
    }
}
