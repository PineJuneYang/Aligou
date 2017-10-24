package com.alg.ailigou.pages.union.entrance;

import com.alg.ailigou.common.api.union.UniconApi;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.common.requestmodel.SearchUnionRequest;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.union
 * Created by Chris Chen on 2017/7/24 10:19.
 * Explain:联盟商家
 */

public class UnionPresenter implements UnionContracts.Presenter {
    private UnionContracts.View view;

    public int page=1,pageSize=20;

    @Inject
    public UnionPresenter() {
    }

    @Override
    public void bindView(UnionContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData(String keyWords) {
        UniconApi.getUnionStoreList(keyWords, page,pageSize,new NetCallback<PageModel<UnionModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<UnionModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notify(netResponse.data);
                }
            }
        });
    }

    @Override
    public void loadSearchData(SearchUnionRequest request) {
        UniconApi.getSearchUnionStoreList(request,new NetCallback<PageModel<UnionModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<UnionModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notify(netResponse.data);
                }
            }
        });
    }
}
