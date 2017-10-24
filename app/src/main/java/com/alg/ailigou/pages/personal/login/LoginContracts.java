package com.alg.ailigou.pages.personal.login;

import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.base.mvp.BaseView;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by 玖泞
 * on 2017/8/7
 * 此类或接口用于 登录界面的契合类
 */

public interface LoginContracts {

    interface View extends BaseView {

        void successfulLogin(String token);

        void failedLogin();

    }

    interface Presenter extends BasePresenter<View> {

        void login(String phoneNumber, String password);

        /**
         * @param platform qq  微信  微博
         * @param action
         * @param data     里面ed数据
         */
        void thirdLogin(SHARE_MEDIA platform, int action, Map<String, String> data);
    }
}
