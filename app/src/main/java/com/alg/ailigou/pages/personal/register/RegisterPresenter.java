package com.alg.ailigou.pages.personal.register;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/7
 * 此类或接口用于
 */

public class RegisterPresenter implements RegisterContracts.Presnter{


    private RegisterContracts.View view ;

    @Inject
    public RegisterPresenter(){}

    @Override
    public void bindView(RegisterContracts.View view) {
        this.view  = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void register(String phoneNumber, String vCode, String pwd) {

        NetCallback callback = new NetCallback() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                if (netResponse.isSuccess){
                    view.successRegister();
                }else{
                    view.failedRegister();
                }
            }
        };
        UserApi.register(phoneNumber,vCode,pwd,callback);
    }

    @Override
    public void getVCode(String phoneNumber,int type) {
        UserApi.getVCode(phoneNumber,type, new NetCallback<Object>() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                if (netResponse.isSuccess){
                    // TODO: 2017/8/28
                    view.successGetCode();
                }else {
                    view.failedGetCode();
                }
            }
        });
    }
}
