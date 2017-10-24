package com.alg.ailigou.pages.personal.mycollection;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/8/8
 * 此类或接口用于
 */

public interface MyCollectionContracts {
    interface View extends BaseView{
        void updateTitles(List<String> titles);
    }

    interface Presenter extends BasePresenter<View>{

        void loadTitle();

    }
}
