package com.alg.ailigou.pages.personal.refundsoraftersale;

import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/17
 * 此类或接口用于
 */

public interface RefundsOrAfterSaleContracts {
    interface View extends BaseView{

        void notify(PageModel<ReturnGoodsData> data);
    }

    interface Presenter extends BasePresenter<View>{

        void loadRefundsAfterSale(int page,int pageSize);
    }
}
