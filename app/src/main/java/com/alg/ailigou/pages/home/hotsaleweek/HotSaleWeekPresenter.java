package com.alg.ailigou.pages.home.hotsaleweek;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.pages.home.consts.HomeConsts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/7/19.
 * 此类或接口用于
 */

public class HotSaleWeekPresenter implements HotSaleWeekContracts.Presenter,HomeConsts {
    private HotSaleWeekContracts.View view;
    public int page= 0,pageSize = 20;


    private List<String> tabTitles = new ArrayList<>();

    @Inject
    public HotSaleWeekPresenter() {

    }

    @Override
    public void bindView(HotSaleWeekContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadActionBannerData() {
        //加载数据
//        NetCallback<List<CommodityModel>> callback = new NetCallback<List<CommodityModel>>() {
//            @Override
//            protected void onComplete(NetResponse<List<CommodityModel>> netResponse) {
//                if (netResponse.isSuccess) {
//                    view.updateTopImageViewData(netResponse.data);
//                }
//            }
//        };
        NetCallback<HomeHotSaleCommodityModel> callback = new NetCallback<HomeHotSaleCommodityModel>() {
            @Override
            protected void onComplete(NetResponse<HomeHotSaleCommodityModel> netResponse) {
                if (netResponse.isSuccess){
                    view.updateTopImageViewData(netResponse.data);
                }
            }
        };
        HomeApi.getHotSaleGoodsList(page,pageSize,callback);
    }

    @Override
    public void loadTabLayoutTitle() {

        for (int i =0;i<HOME_HOT_SALE_WEEK.length;i++){
            tabTitles.add(HOME_HOT_SALE_WEEK[i]);
        }
        if (tabTitles!=null&&tabTitles.size()>0){
            view.updateTabLayoutTitle(tabTitles);
        }
    }
}
