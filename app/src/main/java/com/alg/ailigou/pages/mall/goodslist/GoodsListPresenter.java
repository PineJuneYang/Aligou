package com.alg.ailigou.pages.mall.goodslist;

import com.alg.ailigou.pages.mall.consts.MallConsts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/7/22
 * 此类或接口用于
 */

public class GoodsListPresenter implements GoodsListContracts.Presenter ,MallConsts{

    private  GoodsListContracts.View view;

    private List<String> tabTitles = new ArrayList<>();


    @Inject
    public GoodsListPresenter(){}


    @Override
    public void bindView(GoodsListContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {

    }


    @Override
    public void loadTabTitles() {
        for (int i = 0; i<MALL_GOODS_LIST.length;i++){
            tabTitles.add(MALL_GOODS_LIST[i]);
        }
        if (tabTitles!=null&&tabTitles.size()>0){
            view.updateTablayoutTitle(tabTitles);
        }

    }
}
