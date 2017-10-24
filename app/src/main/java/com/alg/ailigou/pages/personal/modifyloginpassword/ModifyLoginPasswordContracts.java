package com.alg.ailigou.pages.personal.modifyloginpassword;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public interface ModifyLoginPasswordContracts {
    interface View extends BaseView {
        void updataSuccess();

        void showToast(int content);

       void goToNextActivity();


        void showCountdown();
    }

    interface Presenter extends BasePresenter<View> {
        void updatePassword(String oldPwd, String newPwd);

        void getVCode(String phoneNumber, int type);

        /**
         * 校验手机
         */
        void verifyPhone();
    }
}
