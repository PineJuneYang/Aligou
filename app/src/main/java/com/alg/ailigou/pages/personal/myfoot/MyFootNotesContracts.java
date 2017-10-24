package com.alg.ailigou.pages.personal.myfoot;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public interface MyFootNotesContracts {
    interface View extends BaseView {
        void notify(PageModel<CommodityModel> pageModel);

        /**
         * 全部清空后的
         */
        void setViewForEmpty();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 清空足迹
         */
        void deleteAllData();

        void getListData(int page, int pageSize);
    }
}
