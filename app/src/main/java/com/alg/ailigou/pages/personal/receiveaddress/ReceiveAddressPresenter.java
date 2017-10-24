package com.alg.ailigou.pages.personal.receiveaddress;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.ShippingAddressModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/14
 * 此类或接口用于
 */

public class ReceiveAddressPresenter implements ReceiveAddressContracts.Presenter {
    private ReceiveAddressContracts.View view;


    @Inject
    public ReceiveAddressPresenter() {

    }

    @Override
    public void bindView(ReceiveAddressContracts.View view) {
        this.view = view;

    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    public void loadReceiveAddress() {


        NetCallback<List<ShippingAddressModel>> callback = new NetCallback<List<ShippingAddressModel>>() {
            @Override
            protected void onComplete(NetResponse<List<ShippingAddressModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notifyAddress(netResponse.data);
                }
            }
        };


        UserApi.getAddress(callback);
    }

    public void deleteReceiveAddress(long id) {
        UserApi.deleteReceiveAddress(id, new NetCallback() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                if (netResponse.isSuccess) {
              
                }
            }
        });
    }
}
