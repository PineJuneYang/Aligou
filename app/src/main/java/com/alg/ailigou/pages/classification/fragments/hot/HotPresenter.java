package com.alg.ailigou.pages.classification.fragments.hot;

import com.alg.ailigou.common.api.classification.ClassificationApi;
import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.classification.fragments.hot
 * Created by Chris Chen on 2017/7/11 13:09.
 * Explain:热卖推荐
 */

public class HotPresenter implements HotContracts.Presenter {
    private HotContracts.View view;

    public int page=1, pageSize=20;

    @Inject
    public HotPresenter() {
    }

    @Override
    public void bindView(HotContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadCommodityBannerData() {
        NetCallback<List<BannerModel>> callback = new NetCallback<List<BannerModel>>() {
            @Override
            protected void onComplete(NetResponse<List<BannerModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateCommodityBannerData(netResponse.data);
                }
            }
        };
        HomeApi.getBannerData(3, callback);
    }

    @Override
    public void loadNewCommodityData() {
        NetCallback<PageModel<CommodityTypeModel>> callback = new NetCallback<PageModel<CommodityTypeModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityTypeModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateNewCommodityData(netResponse.data.dataList);
                }
            }
        };
        ClassificationApi.getGoodsTypePage(0,page,pageSize,1,callback);
    }

    @Override
    public void loadGoodCommodityData() {
        NetCallback<PageModel<CommodityTypeModel>> callback = new NetCallback<PageModel<CommodityTypeModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityTypeModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateGoodCommodityData(netResponse.data.dataList);
                }
            }
        };
        ClassificationApi.getGoodsTypePage(0,page,pageSize,2,callback);
    }

//    @Override
//    public void loadFashionCommodityData() {
//        NetCallback<PageModel<CommodityTypeModel>> callback = new NetCallback<PageModel<CommodityTypeModel>>() {
//            @Override
//            protected void onComplete(NetResponse<PageModel<CommodityTypeModel>> netResponse) {
//                if (netResponse.isSuccess) {
//                    view.updateFashionCommodityData(netResponse.data.dataList);
//                }
//            }
//        };
//        ClassificationApi.getGoodsTypePage(1,page,pageSize,1,callback);
//    }
}
