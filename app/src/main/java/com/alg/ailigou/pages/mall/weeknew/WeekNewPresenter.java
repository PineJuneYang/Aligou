package com.alg.ailigou.pages.mall.weeknew;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.model.WeekNewGoodsDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.weeknew
 * Created by Chris Chen on 2017/7/21 16:11.
 * Explain:每周上新
 */

public class WeekNewPresenter implements WeekNewContracts.Presenter {
    private WeekNewContracts.View view;

    public int page = 1,pageSize = 20;

    public boolean hasNext;

    @Inject
    public WeekNewPresenter() {
    }

    @Override
    public void bindView(WeekNewContracts.View mView) {
        this.view = mView;
    }

    @Override
    public void unbindView() {

    }

    @Override
    public void loadData() {
        NetCallback<WeekNewGoodsDataModel> callback  =new NetCallback<WeekNewGoodsDataModel>() {
            @Override
            protected void onComplete(NetResponse<WeekNewGoodsDataModel> netResponse) {

                if (netResponse.isSuccess){
                    view.refreshComplete();
                    view.updateWeekNewActivity(netResponse.data);
                }

            }
        };

        if (page==1){
            HomeApi.getWeekNewGoodsData(page,pageSize,callback);
        }else{
            if (hasNext){
                HomeApi.getWeekNewGoodsData(page,pageSize,callback);

            }
        }

//        HomeApi.getWeekNewGoodsData(page,pageSize,callback);

    }
}
