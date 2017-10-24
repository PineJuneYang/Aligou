package com.alg.ailigou.pages.cart.newpush;

import com.alg.ailigou.common.api.cart.CartApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/1
 * 此类或接口用于
 */

public class NewGoodsPushPresenter implements NewGoodsPushContracts.Presenter {


    private NewGoodsPushContracts.View view;


    int page=1,pageSize = 20;


    @Inject
    public NewGoodsPushPresenter(){}


    @Override
    public void bindView(NewGoodsPushContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view =null;
    }

    @Override
    public void loadNewGoodsPushData() {

        NetCallback<PageModel<CommodityModel>> callback=new NetCallback<PageModel<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityModel>> netResponse) {
                if (netResponse.isSuccess){

                    view.update(netResponse.data);
                }
            }
        };

        CartApi.getNewGoodsPush(page,pageSize,callback);

    }
}
