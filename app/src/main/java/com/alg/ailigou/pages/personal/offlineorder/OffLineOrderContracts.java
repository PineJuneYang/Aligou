package com.alg.ailigou.pages.personal.offlineorder;

import com.alg.ailigou.common.model.OrderOffLineDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/16
 * 此类或接口用于
 */

public interface OffLineOrderContracts {

    interface View extends BaseView{

        void notify(PageModel<OrderOffLineDataModel> data);
    }

    interface Presenter extends BasePresenter<View>{

        void queryOffLineOrder(int page, int pageSize, long beginTime, long endTime);
    }
}
