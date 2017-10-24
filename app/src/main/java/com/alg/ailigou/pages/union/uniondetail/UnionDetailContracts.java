package com.alg.ailigou.pages.union.uniondetail;

import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 海航
 * on 2017/7/27.
 * 此类或接口用于
 */

public class UnionDetailContracts {
    public interface View extends BaseView {
        void notify(UnionModel unionModel);
    }

    public interface Presenter extends BasePresenter<View> {
        void loadData(long id);
    }
}
