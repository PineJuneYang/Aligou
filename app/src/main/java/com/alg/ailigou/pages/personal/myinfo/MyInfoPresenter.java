package com.alg.ailigou.pages.personal.myinfo;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/4
 * 此类或接口用于
 */

public class MyInfoPresenter implements MyInfoContracts.Presenter{


    private MyInfoContracts.View view;

    @Inject
    public MyInfoPresenter(){}


    @Override
    public void bindView(MyInfoContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view =null;
    }

    @Override
    public void updataUserInfo(int sex, String imgUrl,String name) {
        UserApi.updataUserInfo(sex, imgUrl, name, new NetCallback<Object>() {
            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess){

                }
            }
        });
    }
}
