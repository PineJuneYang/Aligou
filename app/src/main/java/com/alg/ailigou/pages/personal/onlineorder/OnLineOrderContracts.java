package com.alg.ailigou.pages.personal.onlineorder;

import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/8/16
 * 此类或接口用于
 */

public interface OnLineOrderContracts {
    interface View extends BaseView{

        void notify(PageModel<OrderDetailsDataModel> data);
    }
    interface Presenter extends BasePresenter<View>{

        void queryOnlineOrder(int page, int pageSize, long beginTime, long endTime, String status);

        List<String> getTypes();
    }

}
