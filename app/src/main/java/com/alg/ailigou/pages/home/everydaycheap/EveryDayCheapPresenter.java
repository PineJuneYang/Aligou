package com.alg.ailigou.pages.home.everydaycheap;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/7/19.
 * 此类或接口用于
 */

public class EveryDayCheapPresenter implements EveryDayCheapContracts.Presenter {
    private EveryDayCheapContracts.View view;

    @Inject
    public EveryDayCheapPresenter() {

    }

    @Override
    public void bindView(EveryDayCheapContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData() {
        HomeApi.getEverydayCheapList(new NetCallback<List<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<List<CommodityModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notifity(netResponse.data);
                }
            }
        });
    }
}
