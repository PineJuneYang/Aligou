package com.alg.ailigou.pages.personal.modifyloginpassword;

import com.alg.ailigou.R;
import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public class ModifyLoginPasswordPresenter implements ModifyLoginPasswordContracts.Presenter {
    ModifyLoginPasswordContracts.View view;

    @Inject
    public ModifyLoginPasswordPresenter() {
    }

    @Override
    public void bindView(ModifyLoginPasswordContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void updatePassword(String oldPwd, String newPwd) {
        UserApi.updatePassword(oldPwd, newPwd, new NetCallback<Object>() {

            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess) {
                    view.goToNextActivity();
                }
            }
        });
    }

    @Override
    public void getVCode(String phoneNumber, int type) {
        UserApi.getVCode(phoneNumber, type, new NetCallback() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                if (netResponse.isSuccess){
                   view.showToast(R.string.alg_app_vcode_success);

                }else {
                    view.showToast(R.string.alg_app_vcode_failure);
                }
            }
        });
    }

    @Override
    public void verifyPhone() {
        view.showToast(R.string.alg_app_vcode_verify_failure);
    }
}
