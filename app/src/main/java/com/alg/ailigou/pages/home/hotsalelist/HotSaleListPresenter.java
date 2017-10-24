package com.alg.ailigou.pages.home.hotsalelist;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.pages.home.consts.HomeConsts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by 玖泞
 * on 2017/7/20
 * 此类或接口用于热销榜单
 */

public class HotSaleListPresenter implements HotSaleListContracts.Presenter ,HomeConsts {


    private HotSaleListContracts.View view;
    public int page= 1,pageSize = 20;

    private List<String> tabTitles = new ArrayList<>();
    @Inject
    public HotSaleListPresenter(){

    }

    @Override
    public void bindView(HotSaleListContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadHotSaleGoodsData(int page,int pageSize) {
        //根据不同的position ,请求不同的接口
        NetCallback<HomeHotSaleCommodityModel> callback = new NetCallback<HomeHotSaleCommodityModel>() {
            @Override
            protected void onComplete(NetResponse<HomeHotSaleCommodityModel> netResponse) {
                if (netResponse.isSuccess){
                    view.refreshComplete();
                    view.updateHotSaleData(netResponse.data);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                view.refreshComplete();
            }
        };
        HomeApi.getHotSaleGoodsList(page,pageSize,callback);

    }

    @Override
    public void loadTabLayoutTitle() {

        for (int i =0;i<HOME_HOT_SALE_LIST_TAB_TITLE.length;i++){
            tabTitles.add(HOME_HOT_SALE_LIST_TAB_TITLE[i]);
        }

        view.updateTablayoutTitle(tabTitles);
        

    }


}
