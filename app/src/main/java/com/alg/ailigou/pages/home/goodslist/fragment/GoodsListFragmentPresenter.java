package com.alg.ailigou.pages.home.goodslist.fragment;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.api.mall.MallApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.pages.home.hotsaleweek.fragment.HotSaleWeekFragmentContracts;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/7/21
 * 此类或接口用于
 */

public class GoodsListFragmentPresenter implements GoodsListFragmentContracts.Presenter {


    private GoodsListFragmentContracts.View view;

    public int page =0,pageSize = 20;


    @Override
    public void bindView(GoodsListFragmentContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view  =null;
    }

    @Inject
    public GoodsListFragmentPresenter(){

    }


    @Override
    public void loadData() {

        //// TODO: 2017/7/28  商品列表的接口还没出来.先暂时使用搜索
        NetCallback<PageModel<CommodityModel>> callback = new NetCallback<PageModel<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityModel>> netResponse) {
                if (netResponse.isSuccess){
                    view.notify(netResponse.data);
                }
            }
        };
        HomeApi.getSearchGoodsList("所有商品" ,1, page, pageSize, callback);

    }
}
