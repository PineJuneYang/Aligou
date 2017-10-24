package com.alg.ailigou.pages.personal.editaddress;

import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/8/15
 * 此类或接口用于
 */

public interface EditAddressContracts {
    interface View extends BaseView{
        void setCityData(int type, List<CityModel> cityModels);

    }

    interface Presenter extends BasePresenter<View>{

        /**
         * 获取城市列表
         */
        void loadCityList(int parentId, int type);
    }
}
