package com.alg.ailigou.pages.union.unionsearch;

import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.common.model.UnionTypeModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/29.
 * 此类或接口用于
 */

public class UnionSearchContracts {
    public interface View extends BaseView {
        void setCityData(int type, List<CityModel> cityModels);

        void setUnionTypeData(List<UnionTypeModel> unionTypeData);
    }

    public interface Presenter extends BasePresenter<View> {
        /**
         * 获取商家分类
         */
        void loadUnionTypeList();

        /**
         * 获取城市列表
         */
        void loadCityList(int parentId, int type);
    }
}
