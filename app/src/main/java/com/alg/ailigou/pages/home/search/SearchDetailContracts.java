package com.alg.ailigou.pages.home.search;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/20
 * 此类或接口用于
 */

public interface SearchDetailContracts {
    interface View extends BaseView {
        void updateTablayoutTitle(List<String> tabTitles);

    }
    interface Presenter extends BasePresenter<View> {


        void loadTabLayoutTitle();
    }
}
