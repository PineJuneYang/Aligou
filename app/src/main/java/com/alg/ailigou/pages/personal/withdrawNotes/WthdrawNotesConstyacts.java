package com.alg.ailigou.pages.personal.withdrawNotes;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 海航
 * on 2017/8/7.
 * 此类或接口用于
 */

public class WthdrawNotesConstyacts {
    interface View extends BaseView {
        void notify(Object titles);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * @param timeFrom 起始时间
         * @param timeTo   结束时间
         *
         */
        void loadData(long timeFrom, long timeTo);


    }
}
