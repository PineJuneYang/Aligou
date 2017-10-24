package com.alg.ailigou.pages.personal.addaddress;

import com.alg.ailigou.common.api.union.UniconApi;
import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/15
 * 此类或接口用于
 */

public class AddAddressPresenter implements AddAddressContracts.Presenter {

    private AddAddressContracts.View view;

    @Inject
    public AddAddressPresenter(){}

    @Override
    public void bindView(AddAddressContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }


    public void addAddress(int type,String s, String s1, CityModel provinceModel, CityModel cityModel, CityModel districtModel, String s5, boolean isDefault) {

        NetCallback callback = new NetCallback() {
            @Override
            protected void onComplete(NetResponse netResponse) {
                if (netResponse.isSuccess){
                    view.updateSuccessful();
                }
            }
        };


        UserApi.updateAddress(type,s,s1,provinceModel,cityModel,districtModel,s5,isDefault,callback);
    }

    @Override
    public void loadCityList(int parentId, final int type) {

        UniconApi.getCityList(parentId, new NetCallback<List<CityModel>>() {
            @Override
            protected void onComplete(NetResponse<List<CityModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.setCityData(type,netResponse.data);
                }
            }
        });


    }
}
