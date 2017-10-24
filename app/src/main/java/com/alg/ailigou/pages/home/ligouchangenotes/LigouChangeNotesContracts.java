package com.alg.ailigou.pages.home.ligouchangenotes;

import com.alg.ailigou.common.model.LigouExchangeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/28
 * 此类或接口用于
 */

public interface LigouChangeNotesContracts {
    interface View extends BaseView {
        void notify(PageModel<LigouExchangeModel> pageModel);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * @param timeFrom 起始时间
         * @param timeTo   结束时间
         * @param staus    处理状态
         */
        void loadData(long timeFrom, long timeTo, int staus, int page, int pageSize);

        List<String> getStatuList();

    }

}
