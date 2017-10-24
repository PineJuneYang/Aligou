package com.alg.ailigou.pages.personal.editaddress;

import com.alg.ailigou.common.api.union.UniconApi;
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

public class EditAddressPresenter implements EditAddressContracts.Presenter{

    private EditAddressContracts.View view;

    @Inject
    public EditAddressPresenter(){}

    @Override
    public void bindView(EditAddressContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view=null;
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
