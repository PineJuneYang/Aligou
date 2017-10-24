package com.alg.ailigou.pages.personal.entrance;

import com.alg.ailigou.common.model.UserModel;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class PersonalContrats {
    interface View extends BaseView {
        /**
         * 拿到数据之后填充页面
         *
         * @param data
         */
        void setViewData(UserModel data);
    }

    interface Presenter extends BasePresenter<View> {
        void loadUserInfo();

    }
}
