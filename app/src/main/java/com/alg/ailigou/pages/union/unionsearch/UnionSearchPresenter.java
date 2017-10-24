package com.alg.ailigou.pages.union.unionsearch;

import com.alg.ailigou.common.api.union.UniconApi;
import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.common.model.UnionTypeModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/7/29.
 * 此类或接口用于
 */

public class UnionSearchPresenter implements UnionSearchContracts.Presenter {
    @Inject
    public UnionSearchPresenter() {
    }

    private UnionSearchContracts.View view;

    @Override
    public void bindView(UnionSearchContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadUnionTypeList() {
        UniconApi.getUnionTypeList(new NetCallback<List<UnionTypeModel>>() {
            @Override
            protected void onComplete(NetResponse<List<UnionTypeModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.setUnionTypeData( netResponse.data);
                }
            }
        });
    }

    @Override
    public void loadCityList(int parentId, final int type) {
        UniconApi.getCityList(parentId, new NetCallback<List<CityModel>>() {
            @Override
            protected void onComplete(NetResponse<List<CityModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.setCityData(type, netResponse.data);
                }
            }
        });

    }
}
