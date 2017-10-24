package com.alg.ailigou.pages.personal.mycollection.fragment;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by 玖泞
 * on 2017/8/8
 * 此类或接口用于
 */

public class MyCollectionFragmentPresenter implements MyCollectionFragmentContracts.Presenter{

    private MyCollectionFragmentContracts.View view;

    public int commodityPage = 1,commodityPageSize = 20;

    public int businessPage = 1,businessPageSize =  20;

    public int contentPage= 1,contentPageSize = 20;


    @Inject
    public MyCollectionFragmentPresenter(){}

    @Override
    public void bindView(MyCollectionFragmentContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadCommodity() {
        //加载商品的请求


        NetCallback<PageModel<CommodityModel>> callback = new NetCallback<PageModel<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityModel>> netResponse) {
                if (netResponse.isSuccess){
                    view.refreshComplete();
                    view.updateCommodity(netResponse.data);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                view.refreshComplete();
            }
        };

        UserApi.getCollectionGoodsList(commodityPage,commodityPageSize,callback);
    }

    @Override
    public void loadBusiness(String keyWords) {
        //加载商家的请求
        UserApi.getCollectionUnionList( businessPage,businessPageSize,new NetCallback<PageModel<UnionModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<UnionModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.refreshComplete();
                    view.notifyBusiness(netResponse.data);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                view.refreshComplete();
            }
        });


    }

    @Override
    public void loadContent(int type) {
        //加载内容的请求
        UserApi.getCollectionNewsList(contentPage, contentPageSize, new NetCallback<PageModel<NewsModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<NewsModel>> netResponse) {
                if (netResponse.isSuccess) {
                    //view.notifyContent(netResponse.data);
                }
            }
        });

    }

    @Override
    public void deleteCollectionCommidity(List<Long> ids) {
        NetCallback<PageModel<Object>> callback = new NetCallback<PageModel<Object>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<Object>> netResponse) {
                if (netResponse.isSuccess){
                    view.successfulDeleteGoods();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                view.failDeleteGoods();
            }
        };

        UserApi.deleteCollectionGoods(ids,callback);
    }

    @Override
    public void clearCollectionBusiness(List<Long> unionIds) {




        UserApi.deleteCollectionUnions(unionIds, new NetCallback<Object>() {
            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess){

                    view.successfulDeleteUnions();

                }
            }
        });

    }

}
