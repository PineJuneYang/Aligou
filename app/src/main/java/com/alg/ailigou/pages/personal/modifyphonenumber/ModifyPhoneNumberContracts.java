package com.alg.ailigou.pages.personal.modifyphonenumber;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public interface ModifyPhoneNumberContracts {
    interface View extends BaseView {
        void gotoNext();

        void showCountdown();
    }

    interface Presenter extends BasePresenter<View> {
        void getVcode(String phoneNumber, int type);

        /**
         * 校验身份
         *
         * @param phoneNumber
         * @param vCode
         */
        void verifyID(String phoneNumber, String vCode);
    }
}
