package com.alg.ailigou.pages.personal.resetloginpassword;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public class ResetLoginPasswordPresenter implements ResetLoginPasswordConstracts.Presenter {

    private ResetLoginPasswordConstracts.View view;

    @Inject
    public ResetLoginPasswordPresenter() {
    }

    @Override
    public void bindView(ResetLoginPasswordConstracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void getVCode(String phoneNumber,int type) {
        NetCallback callback = new NetCallback() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                if (netResponse.isSuccess) {
                    //请求成功
                    view.successfulGetVCode();
                } else if (!netResponse.isSuccess) {
                    view.FailedGetVCode();
                }

            }
        };

        UserApi.getVCode(phoneNumber,type, callback);
    }

    @Override
    public void resetLoginPwd(String phoneNunmber, String vCode, String newPassword, int type) {
        NetCallback callback = new NetCallback() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                if (netResponse.isSuccess) {
                    view.successfulResetPwd();
                } else {
                    view.failedResetPwd();
                }
            }
        };
        if (type == 0) {//重置登录密码
            UserApi.resetSave(phoneNunmber, vCode, newPassword, callback);
        } else if (type == 1) {//修改支付密码
            UserApi.updatePayPassword(phoneNunmber, vCode, newPassword, callback);
        }

    }
}
