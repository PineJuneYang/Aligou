package com.alg.ailigou.pages.mall.goodslist;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/22
 * 此类或接口用于
 */

public interface GoodsListContracts {
    interface View extends BaseView{

        void updateTablayoutTitle(List<String> tabTitles);

    }


    interface Presenter extends BasePresenter<View>{

        void loadTabTitles();

    }

}
