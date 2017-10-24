package com.alg.ailigou.pages.home.search;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.pages.home.consts.HomeConsts;
import com.alg.ailigou.pages.home.hotsalelist.HotSaleListContracts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/7/20
 * 此类或接口用于
 */

public class SearchDetailPresenter implements SearchDetailContracts.Presenter ,HomeConsts {


    private SearchDetailContracts.View view;
    private List<String> tabTitles = new ArrayList<>();

    @Inject
    public SearchDetailPresenter(){}



    @Override
    public void bindView(SearchDetailContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {

    }



    //获取tablayout的几个title
    @Override
    public void loadTabLayoutTitle() {


        for (int i =0;i<HomeConsts.HOME_SEARCH_ACTIVITY.length;i++){
            tabTitles.add(HOME_SEARCH_ACTIVITY[i]);
        }

        view.updateTablayoutTitle(tabTitles);
    }
}
