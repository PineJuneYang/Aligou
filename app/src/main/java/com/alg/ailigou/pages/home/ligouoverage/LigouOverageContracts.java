package com.alg.ailigou.pages.home.ligouoverage;

import com.alg.ailigou.common.model.LigouOverageModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 海航
 * on 2017/7/28
 * 此类或接口用于
 */

public interface LigouOverageContracts {
    interface View extends BaseView{
        void notify(PageModel<LigouOverageModel> titles);

    }

    interface Presenter extends BasePresenter<View>{

        void loadData(long startTime,long endTime,int page,int pageSize);

    }

}
