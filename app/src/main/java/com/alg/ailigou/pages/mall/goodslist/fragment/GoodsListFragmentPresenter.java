package com.alg.ailigou.pages.mall.goodslist.fragment;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.api.mall.MallApi;
import com.alg.ailigou.common.model.CommodityModel;
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




    @Override
    public void bindView(GoodsListFragmentContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {

    }

    @Inject
    public GoodsListFragmentPresenter(){

    }

    @Override
    public void loadData(int positon) {
       //根据不同的position ,请求不同的接口
        NetCallback<List<CommodityModel>> callback = new NetCallback<List<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<List<CommodityModel>> netResponse) {
                if (netResponse.isSuccess){
                    view.notify(netResponse.data);
                }
            }
        };

//        HomeApi.getHotSaleFragmentWeek(callback);

        MallApi.getGoodsListFragmentData(callback);



    }
}
