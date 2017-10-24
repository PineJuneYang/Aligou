package com.alg.ailigou.pages.home.ligouchangedetails;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 海航
 * on 2017/7/28
 * 此类或接口用于
 */

public interface LigouChangeDetailsContracts {
    interface View extends BaseView{
        void notify(Object titles);
    }

    interface Presenter extends BasePresenter<View>{

        void loadData();


    }

}
