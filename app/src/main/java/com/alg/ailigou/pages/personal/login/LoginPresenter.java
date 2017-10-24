package com.alg.ailigou.pages.personal.login;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.library.utils.SpUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/7
 * 此类或接口用于 登录的网络请求的业务逻辑层
 */

public class LoginPresenter implements LoginContracts.Presenter {


    private LoginContracts.View view;


    @Inject
    public LoginPresenter() {
    }


    @Override
    public void bindView(LoginContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void login(String phoneNumber, String password) {

        NetCallback<String> callback = new NetCallback<String>() {
            @Override
            protected void onComplete(NetResponse<String> netResponse) {
                if (netResponse.isSuccess) {
                    if (netResponse.code== 0) {

                        //登录成功的回调
                        view.successfulLogin(netResponse.data);
                        SpUtils.save("token",netResponse.data);
                    }
                } else if (!netResponse.isSuccess) {
                    if (netResponse.state == 2) {
                        //登录失败的回调
                        view.failedLogin();
                    }
                }

            }
        };


        UserApi.login(phoneNumber, password, callback);
    }

    @Override
    public void thirdLogin(SHARE_MEDIA platform, int action, Map<String, String> data) {
        switch (platform) {
            case QQ:
                break;
            case WEIXIN:
                break;
            case SINA:
                break;
        }
        String uid = data.get("uid");
        String name = data.get("name");
        String gender = data.get("gender");
        String iconurl = data.get("iconurl");

    }


}
