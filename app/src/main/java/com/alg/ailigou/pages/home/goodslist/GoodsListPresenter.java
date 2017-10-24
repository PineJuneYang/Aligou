package com.alg.ailigou.pages.home.goodslist;

import com.alg.ailigou.pages.home.consts.HomeConsts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/7/28
 * 此类或接口用于
 */

public class GoodsListPresenter implements GoodsListContracts.Presenter,HomeConsts{

    private GoodsListContracts.View mView;
    private List<String> tabs  =new ArrayList<>();

    @Inject
    public GoodsListPresenter(){}

    @Override
    public void bindView(GoodsListContracts.View view) {
       this.mView = view;
    }

    @Override
    public void unbindView() {
        mView = null;
    }


    //加载tabLayout的titles
    @Override
    public void loadTabTitles() {
        for (int i = 0; i<HOME_SEARCH_ACTIVITY.length;i++){
            tabs.add(HOME_SEARCH_ACTIVITY[i]);
        }
        if (tabs.size()>0){
            mView.updateTitles(tabs);
        }
    }


}
