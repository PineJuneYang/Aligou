package com.alg.ailigou.pages.personal.modifyphonenumber;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public class ModifyPhoneNumberPresenter implements ModifyPhoneNumberContracts.Presenter {

    private ModifyPhoneNumberContracts.View view;

    @Inject
    public ModifyPhoneNumberPresenter() {

    }


    @Override
    public void bindView(ModifyPhoneNumberContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void getVcode(String phoneNumber, int type) {
        UserApi.getVCode(phoneNumber, type, new NetCallback() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                //成功之后开始倒计时
                view.showCountdown();
            }
        });
    }

    @Override
    public void verifyID(String phoneNumber, String vCode) {
        view.gotoNext();
    }
}
