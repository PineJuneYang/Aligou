package com.alg.ailigou.pages.classification.entrance;

import com.alg.ailigou.common.api.classification.ClassificationApi;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.classification.entrance
 * Created by Chris Chen on 2017/7/4 15:07.
 * Explain:分类MVP 逻辑处理
 */

public class ClassificationPresenter implements ClassificationContracts.Presenter {
    private ClassificationContracts.View view;
    public int page = 1, pageSize = 20;

    @Inject
    public ClassificationPresenter() {
    }

    @Override
    public void bindView(ClassificationContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadClassicList() {
        NetCallback<List<CommodityTypeModel>> callback = new NetCallback<List<CommodityTypeModel>>() {
            @Override
            protected void onComplete(NetResponse<List<CommodityTypeModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateClassicList(netResponse.data);
                }
            }
        };
        ClassificationApi.getGoodsCategory(callback);
    }
}
