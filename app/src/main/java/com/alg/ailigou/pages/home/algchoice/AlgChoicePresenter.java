package com.alg.ailigou.pages.home.algchoice;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeCommodityTypeModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by 海航
 * on 2017/7/20.
 * 此类或接口用于
 */

public class AlgChoicePresenter implements AlgChoiceContracts.Presenter {
    AlgChoiceContracts.View view;

    @Inject
    public AlgChoicePresenter() {
    }

    @Override
    public void bindView(AlgChoiceContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }


    @Override
    public void loadTopData() {
        HomeApi.getAlgChoiceRecommendList(new NetCallback<List<HomeCommodityTypeModel>>() {
            @Override
            protected void onComplete(NetResponse<List<HomeCommodityTypeModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.setTopData(netResponse.data);
                }
            }
        });
    }

    @Override
    public void loadListData() {
        HomeApi.getAlgChoiceList(new NetCallback<List<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<List<CommodityModel>> netResponse) {
                view.refreshComplete();
                if (netResponse.isSuccess) {
                    view.notifity(netResponse.data);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);
                view.refreshComplete();
            }
        });
    }
}
