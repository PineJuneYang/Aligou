package com.alg.ailigou.pages.home.hotsaleweek.fragment;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.pages.home.hotsalelist.fragment.HotSaleListFragmentContracts;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/7/21
 * 此类或接口用于
 */

public class HotSaleWeekFragmentPresenter implements HotSaleWeekFragmentContracts.Presenter {


    private HotSaleWeekFragmentContracts.View view;


    @Override
    public void bindView(HotSaleWeekFragmentContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {

    }

    @Inject
    public HotSaleWeekFragmentPresenter(){

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

        HomeApi.getHotSaleFragmentWeek(callback);
    }
}
