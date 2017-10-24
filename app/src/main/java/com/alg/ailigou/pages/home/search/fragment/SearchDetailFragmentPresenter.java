package com.alg.ailigou.pages.home.search.fragment;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by 玖泞
 * on 2017/7/21
 * 此类或接口用于
 */

public class SearchDetailFragmentPresenter implements SearchDetailFragmentContracts.Presenter {

    private SearchDetailFragmentContracts.View view;
    public int page=1,pageSize=7;

    @Inject
    public SearchDetailFragmentPresenter(){}

    @Override
    public void bindView(SearchDetailFragmentContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData(String keyWords, int sort) {
        //根据不同的position,拿去不同的orderField,请求不同的接口,填充fragment
        NetCallback<PageModel<CommodityModel>> callback = new NetCallback<PageModel<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityModel>> netResponse) {
                if (netResponse.isSuccess){
                    view.refreshComplete();
                    view.notify(netResponse.data);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                view.refreshComplete();
            }
        };
        HomeApi.getSearchGoodsList(keyWords ,sort,page,pageSize,callback);
    }
}
