package com.alg.ailigou.pages.home.make;

import com.alg.ailigou.common.api.union.UniconApi;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.library.utils.JsonUtils;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/9/1
 * 此类或接口用于
 */

public class BillDetailPresenter implements BillDetailContracts.Presnter {

    private BillDetailContracts.View view;
    @Inject
    public BillDetailPresenter(){}

    @Override
    public void bindView(BillDetailContracts.View view) {
        this.view = view ;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }


    @Override
    public void loadUnionDetail(String businessId) {
        //请求商户信息
        NetCallback<UnionModel> callback = new NetCallback<UnionModel>() {
            @Override
            protected void onComplete(NetResponse<UnionModel> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateUnionData(netResponse.data);
                }
            }
        };
        UniconApi.getUnionDetailsData(Integer.parseInt(businessId), callback);
    }
}
