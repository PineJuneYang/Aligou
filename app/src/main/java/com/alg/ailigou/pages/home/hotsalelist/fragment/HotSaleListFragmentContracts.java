package com.alg.ailigou.pages.home.hotsalelist.fragment;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeHotSaleCommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/21
 * 此类或接口用于
 */

public interface HotSaleListFragmentContracts  {

    interface View extends BaseView{
        void notify(HomeHotSaleCommodityModel commodityModels);
    }

    interface Presenter extends BasePresenter<View>{
        void loadData(int page,int pageSize);
    }
}
