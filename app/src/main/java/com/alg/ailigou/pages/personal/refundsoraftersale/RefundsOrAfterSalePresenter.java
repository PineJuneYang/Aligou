package com.alg.ailigou.pages.personal.refundsoraftersale;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/17
 * 此类或接口用于
 */

public class RefundsOrAfterSalePresenter implements RefundsOrAfterSaleContracts.Presenter {

    private RefundsOrAfterSaleContracts.View view;

    @Inject
    public RefundsOrAfterSalePresenter (){}
    public int page = 1,pageSize = 20;

    @Override
    public void bindView(RefundsOrAfterSaleContracts.View view) {
        this.view  = view;
    }

    @Override
    public void unbindView() {
        this.view =null;
    }

    @Override
    public void loadRefundsAfterSale(int page, int pageSize) {
        NetCallback<PageModel<ReturnGoodsData>> callback = new NetCallback<PageModel<ReturnGoodsData>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<ReturnGoodsData>> netResponse) {
                if (netResponse.isSuccess){
                    view.notify(netResponse.data);
                }
            }
        };

        UserApi.getRefundsAfterSaleList(page,pageSize,callback);
    }
}
