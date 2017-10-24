package com.alg.ailigou.pages.mall.details;

import com.alg.ailigou.common.api.ailigou.AiligouApi;
import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommentDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.details
 * Created by Chris Chen on 2017/7/12 17:57.
 * Explain:商品详情逻辑处理
 */

public class CommodityDetailsPresenter implements CommodityDetailsContracts.Presenter {
    private CommodityDetailsContracts.View view;

    public int page = 1, pageSize = 20;//推荐商品分页参数

    @Inject
    public CommodityDetailsPresenter() {
    }

    @Override
    public void bindView(CommodityDetailsContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadGoodsDetailsData(long goodsId) {
        NetCallback<CommodityModel> callback = new NetCallback<CommodityModel>() {
            @Override
            protected void onComplete(NetResponse<CommodityModel> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateGoodsDetailsData(netResponse.data);
                }
            }
        };
        HomeApi.getGoodsDetailsData(goodsId, callback);
    }

    @Override
    public void loadRecommetPage() {
        NetCallback<PageModel<CommodityModel>> callback = new NetCallback<PageModel<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateRecommetPage(netResponse.data);
                }
            }
        };
        AiligouApi.getRecommendList(5, page, pageSize, callback);
    }

    @Override
    public void loadSpecsData(long id) {
        NetCallback<List<String>> callback = new NetCallback<List<String>>() {
            @Override
            protected void onComplete(NetResponse<List<String>> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateSpecsData(netResponse.data);
                }
            }
        };
        HomeApi.getGoodsAttribute(id, callback);
    }

    @Override
    public void addGoodsToFav(long id) {

        HomeApi.addGoodsToFav(id, new NetCallback<Object>() {
            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess){
                    //// TODO: 2017/8/23
//                    view.showLoading();
                    view.successCollection();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);

            }
        });
    }

    @Override
    public void addGoodsToCart(CommodityModel model) {

        HomeApi.addGoodsToCart(model, new NetCallback<Object>() {
            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess){
                    //// TODO: 2017/8/23
                    view.showLoading();
                }
            }
        });
    }

    @Override
    public void loadCommentData(long goodsId) {
        NetCallback<CommentDataModel> callback = new NetCallback<CommentDataModel>() {
            @Override
            protected void onComplete(NetResponse<CommentDataModel> netResponse) {
                if (netResponse.isSuccess) {
                    view.updateCommentLabelData(netResponse.data);
                }
            }
        };
        HomeApi.getCommentData(goodsId, callback);

    }
}
