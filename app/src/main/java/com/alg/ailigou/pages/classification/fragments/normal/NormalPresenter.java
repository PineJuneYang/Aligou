package com.alg.ailigou.pages.classification.fragments.normal;

import com.alg.ailigou.common.api.classification.ClassificationApi;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.classification.fragments.normal
 * Created by Chris Chen on 2017/7/13 09:13.
 * Explain:一般商品分类逻辑处理
 */

public class NormalPresenter implements NormalContracts.Presenter {
    private NormalContracts.View view;

    int page =1, pageSize = 20;

    @Inject
    public NormalPresenter() {
    }

    @Override
    public void bindView(NormalContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadClassificationList(int typeId) {

        NetCallback<PageModel<CommodityTypeModel>> callback = new NetCallback<PageModel<CommodityTypeModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityTypeModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateFashionCommodityData(netResponse.data);
                }
            }
        };
        ClassificationApi.getGoodsGroup(typeId,page,pageSize,0,callback);

    }
}
