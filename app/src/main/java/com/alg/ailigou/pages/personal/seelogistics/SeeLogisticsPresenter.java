package com.alg.ailigou.pages.personal.seelogistics;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.LogisticsModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/8/4.
 * 此类或接口用于
 */

public class SeeLogisticsPresenter implements SeeLogisticsContracts.Presenter {
    SeeLogisticsContracts.View view;

    @Inject
    public SeeLogisticsPresenter() {
    }

    @Override
    public void bindView(SeeLogisticsContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }


    @Override
    public void loadGoodsData(long id) {
    }

    @Override
    public void loadLogisticsData(String  shipperCode,String logisticCode) {
        // TODO: 2017/8/29
        UserApi.getLogisticsData(shipperCode,logisticCode, new NetCallback<LogisticsModel>() {
            @Override
            protected void onComplete(NetResponse<LogisticsModel> netResponse) {
                      if (netResponse.isSuccess){
                          view.logisticsListNotify(netResponse.data);
                      }
            }
        });


        
    }
}
