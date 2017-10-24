package com.alg.ailigou.pages.classification.entrance;

import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.classification.entrance
 * Created by Chris Chen on 2017/7/4 15:05.
 * Explain:分类dagger2契约
 */

public interface ClassificationContracts {
    interface View extends BaseView {
        void updateClassicList(List<CommodityTypeModel> commodityTypeList);//更新类别列表
    }

    interface Presenter extends BasePresenter<View> {
        void loadClassicList();//加载类别列表
    }
}
