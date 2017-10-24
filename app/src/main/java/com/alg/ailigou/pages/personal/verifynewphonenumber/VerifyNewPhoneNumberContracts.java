package com.alg.ailigou.pages.personal.verifynewphonenumber;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public interface VerifyNewPhoneNumberContracts {
    interface View extends BaseView {
        void showToast(String content);

        void showCountdown();
    }

    interface Presenter extends BasePresenter<View> {
        void updatePhoneNumber(String telNumber, String vCode);

        /**
         * 校验手机号被用过没
         *
         * @param telNumber
         */
        void existPhone(String telNumber, int type);

        /**
         * 获取验证码
         */
        void getVcode(String telNumber, int type);
    }
}
