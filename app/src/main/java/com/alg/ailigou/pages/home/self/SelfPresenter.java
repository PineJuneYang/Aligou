package com.alg.ailigou.pages.home.self;

import com.alg.ailigou.common.api.ailigou.AiligouApi;
import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.SelfGoodsDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.self
 * Created by Chris Chen on 2017/7/13 13:57.
 * Explain:自营
 */

public class SelfPresenter implements SelfContracts.Presenter {
    private SelfContracts.View view;

    @Inject
    public SelfPresenter() {
    }


    @Override
    public void bindView(SelfContracts.View view) {
        this.view=view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadSelfData() {
        HomeApi.getSelfGoodsData(new NetCallback<SelfGoodsDataModel>() {
            @Override
            protected void onComplete(NetResponse<SelfGoodsDataModel> netResponse) {
                if (netResponse.isSuccess) {
                   view.refreshComplete();
                   view.undateSelfData(netResponse.data);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                view.refreshComplete();
            }
        });
    }

    @Override
    public void loadCommendData(int type, int page, int pageSize) {
        AiligouApi.getRecommendList(type, page, pageSize, new NetCallback<PageModel<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.undateCommendData(netResponse.data);
                }
            }
        });
    }

//    @Override
//    public void loadBannerData() {
//        NetCallback<List<BannerModel>> callback = new NetCallback<List<BannerModel>>() {
//            @Override
//            protected void onComplete(NetResponse<List<BannerModel>> netResponse) {
//                if (netResponse.isSuccess) {
//                    view.updateBannerData(netResponse.data);
//                }
//            }
//        };
//        HomeApi.getBannerData(0, callback);
//    }
//
//    @Override
//    public void loadClassicData() {
//
//    }
//
//    @Override
//    public void loadHotData() {
//        NetCallback<List<CommodityModel>> callback = new NetCallback<List<CommodityModel>>() {
//            @Override
//            protected void onComplete(NetResponse<List<CommodityModel>> netResponse) {
//                if (netResponse.isSuccess) {
//                    view.updateHotData(netResponse.data);
//                }
//            }
//        };
//        HomeApi.getSimpleCommodityList(1, callback);
//    }
//
//    @Override
//    public void loadMonthActionData() {
//        NetCallback<List<CommodityModel>> callback = new NetCallback<List<CommodityModel>>() {
//            @Override
//            protected void onComplete(NetResponse<List<CommodityModel>> netResponse) {
//                if (netResponse.isSuccess) {
//                    view.updateMonthActionData(netResponse.data);
//                }
//            }
//        };
//        HomeApi.getSimpleCommodityList(1, callback);
//    }
}
