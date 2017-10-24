package com.alg.ailigou.pages.union.uniondetail;

import com.alg.ailigou.common.api.union.UniconApi;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/7/27.
 * 此类或接口用于
 */

public class UnionDetailPresenter implements UnionDetailContracts.Presenter {
    private UnionDetailContracts.View mView;

    @Inject
    public UnionDetailPresenter() {
    }

    @Override
    public void bindView(UnionDetailContracts.View view) {
        mView = view;
    }

    @Override
    public void unbindView() {
        mView = null;
    }

    @Override
    public void loadData(long id) {

        UniconApi.getUnionDetailsData(id, new NetCallback<UnionModel>() {
            @Override
            protected void onComplete(NetResponse<UnionModel> netResponse) {
                mView.notify(netResponse.data);
            }
        });
    }
}
