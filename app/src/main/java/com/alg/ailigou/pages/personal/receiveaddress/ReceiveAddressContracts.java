package com.alg.ailigou.pages.personal.receiveaddress;

import com.alg.ailigou.common.model.ShippingAddressModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/8/14
 * 此类或接口用于
 */

public interface ReceiveAddressContracts {
    interface View extends BaseView{

        void notifyAddress(List<ShippingAddressModel> data);
    }
    interface Presenter extends BasePresenter<View>{

    }
}
