package com.alg.ailigou.pages.home.hotsalelist.fragment;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/7/21
 * 此类或接口用于
 */

public class HotSaleListFragmentPresenter implements HotSaleListFragmentContracts.Presenter {


    private HotSaleListFragmentContracts.View hotSaleListFragment;
    public int page= 0,pageSize = 20;


    @Override
    public void bindView(HotSaleListFragmentContracts.View view) {
        this.hotSaleListFragment = view;
    }

    @Override
    public void unbindView() {

    }

    @Inject
    public HotSaleListFragmentPresenter(){

    }

    @Override
    public void loadData(int page,int pageSize) {
       //根据不同的position ,请求不同的接口
        NetCallback<HomeHotSaleCommodityModel> callback = new NetCallback<HomeHotSaleCommodityModel>() {
            @Override
            protected void onComplete(NetResponse<HomeHotSaleCommodityModel> netResponse) {
                if (netResponse.isSuccess){
                    hotSaleListFragment.notify(netResponse.data);
                }
            }
        };

        HomeApi.getHotSaleGoodsList(page,pageSize,callback);
    }
}
