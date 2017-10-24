package com.alg.ailigou.pages.personal.addaddress;

import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/8/15
 * 此类或接口用于
 */

public interface AddAddressContracts  {
    interface View extends BaseView{


        void setCityData(int type, List<CityModel> data);

        void updateSuccessful();
    }

    interface Presenter extends BasePresenter<View>{
        void loadCityList(int parentId,int type);
    }

}
