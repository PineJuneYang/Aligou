package com.alg.ailigou.pages.mall.limit;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.ChoiceCheapGoodsDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.limit
 * Created by Chris Chen on 2017/7/21 15:56.
 * Explain:限时特购
 */

public class LimitPresenter implements LimitContracts.Presenter {
    private LimitContracts.View view;

    @Inject
    public LimitPresenter() {
    }

    @Override
    public void bindView(LimitContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void LoadData(int page, int pageSize) {
        HomeApi.getChoiceCheapGoodsData(page, pageSize, new NetCallback<ChoiceCheapGoodsDataModel>() {
            @Override
            protected void onComplete(NetResponse<ChoiceCheapGoodsDataModel> netResponse) {
                view.refreshComplete();
                if (netResponse.isSuccess) {
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
