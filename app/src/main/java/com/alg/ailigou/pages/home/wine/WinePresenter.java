package com.alg.ailigou.pages.home.wine;

import com.alg.ailigou.common.api.ailigou.AiligouApi;
import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeWineDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.wine
 * Created by Chris Chen on 2017/7/13 14:00.
 * Explain:佳酿
 */

public class WinePresenter implements WineContracts.Presenter {
    private WineContracts.View view;

    @Inject
    public WinePresenter() {
    }

    @Override
    public void bindView(WineContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }


    @Override
    public void loadWineData() {
        HomeApi.getHomeWineData(new NetCallback<HomeWineDataModel>() {
            @Override
            protected void onComplete(NetResponse<HomeWineDataModel> netResponse) {
                if (netResponse.isSuccess) {
                    view.wineDataNotifity(netResponse.data);
                }
            }
        });
    }

    @Override
    public void loadRecommendGoodsData(int type,int page,int pageSize) {
        AiligouApi.getRecommendList(0,page,pageSize, new NetCallback<PageModel<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityModel>> netResponse) {
                if (netResponse.isSuccess){
                    view.recommendGoodsNotifity(netResponse.data);
                }
            }
        });
    }
}
