package com.alg.ailigou.pages.home.entrance;

import com.alg.ailigou.common.model.FunctionModel;
import com.alg.ailigou.common.model.HomeDataModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.entrance
 * Created by Chris Chen on 2017/7/4 15:26.
 * Explain:首页MVP契约
 */

public interface HomeContrats {
    interface View extends BaseView {
        void updateData(HomeDataModel homeDataModel);//更新页面所有数据
        void updateFunction(List<FunctionModel> functionModelList);//更新首页功能板块数据
        void updateMoreUnionData(List<UnionModel> unionModelList);//更新更多联盟商家数据
    }

    interface Presenter extends BasePresenter<View> {
        void loadData();//加载数据
        void loadFunction();//加载功能列表
        void loadMoreUnionData();//加载更多联盟商家数据
    }
}
