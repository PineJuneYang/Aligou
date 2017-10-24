package com.alg.ailigou.pages.home.make;

import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/9/1
 * 此类或接口用于
 */

public interface BillDetailContracts {
    interface View extends BaseView{

        void updateUnionData(UnionModel data);
    }
    interface Presnter extends BasePresenter<View>{
        void loadUnionDetail(String businessId);
    }
}
