package com.alg.ailigou.pages.home.ligouoverage;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.LigouOverageModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于 利购券兑换记录
 */

public class LigouOveragePresenter implements LigouOverageContracts.Presenter {
    private LigouOverageContracts.View view;

    @Inject
    public LigouOveragePresenter() {
    }

    @Override
    public void bindView(LigouOverageContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }




    @Override
    public void loadData(long startTime, long endTime, int page, int pageSize) {
        UserApi.getLigouOverageList(startTime, endTime, page, pageSize, new NetCallback<PageModel<LigouOverageModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<LigouOverageModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notify(netResponse.data);
                }
            }
        });
    }
}
