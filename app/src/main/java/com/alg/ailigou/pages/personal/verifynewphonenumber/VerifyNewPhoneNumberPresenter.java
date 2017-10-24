package com.alg.ailigou.pages.personal.verifynewphonenumber;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public class VerifyNewPhoneNumberPresenter implements VerifyNewPhoneNumberContracts.Presenter {

    private VerifyNewPhoneNumberContracts.View view;

    @Inject
    public VerifyNewPhoneNumberPresenter() {
    }


    @Override
    public void bindView(VerifyNewPhoneNumberContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void updatePhoneNumber(String telNumber, String vCode) {
        UserApi.updatePhone(telNumber, vCode, new NetCallback<Object>() {

            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess) {
                    view.showToast("成功");
                }
            }
        });
    }

    @Override
    public void existPhone(final String telNumber, final int type) {
        UserApi.existPhone(telNumber, new NetCallback<Object>() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                if (netResponse.isSuccess) {
                    getVcode(telNumber, type);
                }
            }
        });
    }

    @Override
    public void getVcode(String telNumber, int type) {
        UserApi.getVCode(telNumber, type, new NetCallback() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                if (netResponse.isSuccess) {
                    view.showCountdown();
                    view.showToast("获取验证码成功");
                }
            }
        });
    }
}
