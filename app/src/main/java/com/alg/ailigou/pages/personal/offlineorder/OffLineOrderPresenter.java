package com.alg.ailigou.pages.personal.offlineorder;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.OrderOffLineDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/16
 * 此类或接口用于
 */

public class OffLineOrderPresenter implements OffLineOrderContracts.Presenter {

    private OffLineOrderContracts.View view;
    public int page  = 1,pageSize =20;

    @Inject
    public OffLineOrderPresenter(){}



    @Override
    public void bindView(OffLineOrderContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void queryOffLineOrder(int page, int pageSize, long beginTime, long endTime) {
        NetCallback<PageModel<OrderOffLineDataModel>> callback = new NetCallback<PageModel<OrderOffLineDataModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<OrderOffLineDataModel>> netResponse) {
                if (netResponse.isSuccess){
                    view.notify(netResponse.data);
                }
            }
        };

        UserApi.queryOfflineOrder(page,pageSize,beginTime,endTime,callback);
    }
}
