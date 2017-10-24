package com.alg.ailigou.pages.home.goodslist;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/28
 * 此类或接口用于
 */

public interface GoodsListContracts {
    interface View extends BaseView{
        void updateTitles(List<String> titles);
    }

    interface Presenter extends BasePresenter<View>{

        void loadTabTitles();


    }

}
