package com.alg.ailigou.pages.home.hotrecommend;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.HotRecommendDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by 海航
 * on 2017/7/21.
 * 此类或接口用于
 */

public class HotRecommendPresenter implements HotRecommendContracts.Presenter {
    HotRecommendContracts.View view;

    public int page =1,pageSize = 20;

    @Inject
    public HotRecommendPresenter() {
    }

    @Override
    public void bindView(HotRecommendContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData(int page, int pageSize) {
        HomeApi.getHotRecommendData(page, pageSize, new NetCallback<HotRecommendDataModel>() {
            @Override
            protected void onComplete(NetResponse<HotRecommendDataModel> netResponse) {
                view.refreshComplete();
                if (netResponse.isSuccess){
                    view.notify(netResponse.data);
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                view.refreshComplete();
            }
        });
    }
}
