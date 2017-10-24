package com.alg.ailigou.pages.home.algchoice;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeCommodityTypeModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.ligoupaper
 * Created by Chris Chen on 2017/7/13 13:47.
 * Explain:利购券
 */

public interface AlgChoiceContracts {
    interface View extends BaseView {
        void setTopData(List<HomeCommodityTypeModel> list);

        void notifity(List<CommodityModel> modelList);

    }

    interface Presenter extends BasePresenter<View> {
        void loadTopData();//上面头部的数据

        void loadListData();//下面列表数据
    }
}
