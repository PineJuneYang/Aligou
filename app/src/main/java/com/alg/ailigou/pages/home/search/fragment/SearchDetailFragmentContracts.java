package com.alg.ailigou.pages.home.search.fragment;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/7/21
 * 此类或接口用于
 */

public interface SearchDetailFragmentContracts {

    interface View extends BaseView{
        void notify(PageModel<CommodityModel> commodityModelPageModel);
    }

    interface Presenter extends BasePresenter<View>{
        void loadData(String keyWords, int sort);
    }
}
