package com.alg.ailigou.pages.home.goodslist.fragment;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/21
 * 此类或接口用于
 */

public interface GoodsListFragmentContracts {

    interface View extends BaseView{
        void notify(PageModel<CommodityModel> commodityModelPageModel);
    }

    interface Presenter extends BasePresenter<View>{
        void loadData();
    }
}
