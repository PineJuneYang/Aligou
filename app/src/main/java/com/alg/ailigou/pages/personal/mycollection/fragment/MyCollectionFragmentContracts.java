package com.alg.ailigou.pages.personal.mycollection.fragment;

import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/8/8
 * 此类或接口用于
 */

public interface MyCollectionFragmentContracts {
    interface View extends BaseView{

        void updateCommodity(PageModel<CommodityModel> data);

        void notifyBusiness(PageModel<UnionModel> data);

        void notifyContent(PageModel<NewsModel> dataList);

        void successfulDeleteGoods();

        void failDeleteGoods();

        void successfulDeleteUnions();
    }

    interface Presenter extends BasePresenter<View>{
        void loadCommodity();

        void loadBusiness(String keyWords);

        void loadContent(int type);

        void deleteCollectionCommidity(List<Long> ids);

        void clearCollectionBusiness(List<Long> unionIds);
    }
}
