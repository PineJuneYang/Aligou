package com.alg.ailigou.pages.classification.fragments.normal;

import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.classification.fragments.normal
 * Created by Chris Chen on 2017/7/13 09:11.
 * Explain:一般商品分类
 */

public interface NormalContracts {
    interface View extends BaseView{

        void updateFashionCommodityData(PageModel<CommodityTypeModel> data);
    }
    interface Presenter extends BasePresenter<View>{

        void loadClassificationList(int typeId);

    }
}
