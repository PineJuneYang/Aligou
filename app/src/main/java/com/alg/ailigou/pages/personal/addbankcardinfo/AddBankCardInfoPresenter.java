package com.alg.ailigou.pages.personal.addbankcardinfo;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.BankModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/8/2.
 * 此类或接口用于
 */

public class AddBankCardInfoPresenter implements AddBankCardInfoContrats.Presenter {
    private AddBankCardInfoContrats.View view;

    @Inject
    public AddBankCardInfoPresenter() {
    }

    @Override
    public void bindView(AddBankCardInfoContrats.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadBankData() {

    }

    @Override
    public void commitBankInfo(BankModel model, final int type) {
        UserApi.updateBankCard(model, new NetCallback<Object>() {
            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess) {
                    view.commitFinish(type);
                }
            }
        });
    }

    @Override
    public void removeBankCard() {
        UserApi.removeBankCard(new NetCallback<Object>() {
            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess) {
                    view.toAddCardActivity();
                }
            }
        });
    }
}
