package com.alg.ailigou.pages.personal.moneychange;

import com.alg.ailigou.common.model.CommonTypeModel;
import com.alg.ailigou.common.model.MoneyChangeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/8/4
 * 此类或接口用于
 */

public interface MoneyChangeContracts {
    interface View extends BaseView {
        void notify(PageModel<MoneyChangeModel> datas);
        void showStatuPicker(final List<CommonTypeModel> list);

    }

    interface Presenter extends BasePresenter<View> {
        void loadData(int page, int pageSize, long startTime, long endTime, int type);
        void getStatuList();
    }
}
