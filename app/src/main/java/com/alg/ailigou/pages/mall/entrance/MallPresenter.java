package com.alg.ailigou.pages.mall.entrance;

import com.alg.ailigou.common.api.mall.MallApi;
import com.alg.ailigou.common.model.MallDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.entrance
 * Created by Chris Chen on 2017/7/13 13:32.
 * Explain:商城
 */

public class MallPresenter implements MallContracts.Presenter {
    private MallContracts.View view;

    @Inject
    public MallPresenter() {
    }

    @Override
    public void bindView(MallContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData() {
        NetCallback<MallDataModel> callback = new NetCallback<MallDataModel>() {
            @Override
            protected void onComplete(NetResponse<MallDataModel> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateData(netResponse.data);
                }
            }
        };
        MallApi.getMallData(callback);
    }
}
