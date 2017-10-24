package com.alg.ailigou.pages.personal.register;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/7
 * 此类或接口用于
 */

public interface RegisterContracts {
    interface View extends BaseView {

        void successRegister();

        void failedRegister();

        void successGetCode();

        void failedGetCode();

    }

    interface Presnter extends BasePresenter<View> {

        void register(String phoneNumber, String vCode, String pwd);

        void getVCode(String phoneNumber,int type);
    }
}
