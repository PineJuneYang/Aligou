package com.alg.ailigou.pages.personal.entrance;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.UserModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/7/5.
 */

public class PersonalPresenter implements PersonalContrats.Presenter {
    private PersonalContrats.View view;

    @Inject
    public PersonalPresenter() {
    }

    @Override
    public void bindView(PersonalContrats.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadUserInfo() {
        UserApi.getUserData(new NetCallback<UserModel>() {
            @Override
            protected void onComplete(NetResponse<UserModel> netResponse) {
                if (netResponse.isSuccess) {
                   view.setViewData(netResponse.data);
                }
            }
        });
    }
}
