package com.alg.ailigou.pages.personal.resetloginpassword;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public interface ResetLoginPasswordConstracts {

    interface View extends BaseView{


        void successfulGetVCode();

        void successfulResetPwd();

        void failedResetPwd();

        void FailedGetVCode();
    }


    interface Presenter extends BasePresenter<View>{

        void getVCode(String phoneNumber,int type);

        void resetLoginPwd(String phoneNunmber,String vCode,String newPassword,int type);

    }
}
