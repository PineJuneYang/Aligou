package com.alg.ailigou.pages.home.ligoupaper;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.HomeLigouPaperDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.ligoupaper
 * Created by Chris Chen on 2017/7/13 13:48.
 * Explain:利购券
 */

public class LigouPaperPresenter implements LigouPaperContracts.Presenter {
    private LigouPaperContracts.View view;

    @Inject
    public LigouPaperPresenter() {
    }

    @Override
    public void bindView(LigouPaperContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData(int pager, int pagerSize) {

        HomeApi.getLigouPaperData(pager, pagerSize, new NetCallback<HomeLigouPaperDataModel>() {
            @Override
            protected void onComplete(NetResponse<HomeLigouPaperDataModel> netResponse) {
                if (netResponse.isSuccess){
                    view.notify(netResponse.data);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }
}
