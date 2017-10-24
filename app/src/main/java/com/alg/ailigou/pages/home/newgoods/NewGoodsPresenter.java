package com.alg.ailigou.pages.home.newgoods;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.newsgoods
 * Created by Chris Chen on 2017/7/13 13:52.
 * Explain:新品
 */

public class NewGoodsPresenter implements NewGoodsContracts.Presenter {
    @Inject
    public NewGoodsPresenter() {
    }

    public void loadData(int page, int pageSize) {
        HomeApi.getNewGoodsList(page, pageSize, new NetCallback<PageModel<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityModel>> netResponse) {
                view.refreshComplete();
                if (netResponse.isSuccess) {
                    view.notifity(netResponse.data);
                } else {

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                view.refreshComplete();
            }
        });
    }

    NewGoodsContracts.View view;

    @Override
    public void bindView(NewGoodsContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }
}
