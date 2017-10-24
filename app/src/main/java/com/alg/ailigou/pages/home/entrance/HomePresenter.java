package com.alg.ailigou.pages.home.entrance;

import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.api.union.UniconApi;
import com.alg.ailigou.common.model.FunctionModel;
import com.alg.ailigou.common.model.HomeDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.pages.home.consts.HomeConsts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.entrance
 * Created by Chris Chen on 2017/7/4 15:27.
 * Explain:首页MVP逻辑处理
 */

public class HomePresenter implements HomeContrats.Presenter, HomeConsts {
    private HomeContrats.View view;
    //    @Inject
    List<FunctionModel> functionModelList;
    public int page = 1, pageSize = 5;//分页参数

    @Inject
    public HomePresenter() {
    }

    @Override
    public void bindView(HomeContrats.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData() {
        NetCallback<HomeDataModel> callback = new NetCallback<HomeDataModel>() {
            @Override
            protected void onComplete(NetResponse<HomeDataModel> netResponse) {
                view.refreshComplete();
                if (netResponse.isSuccess && netResponse.data != null) {
                    view.updateData(netResponse.data);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                view.refreshComplete();
                super.onFailure(call, t);
            }
        };
        HomeApi.getHomeData(callback);
    }

    @Override
    public void loadFunction() {
        functionModelList = new ArrayList<>();
        for (int i = 0; i < HOME_FUNCTION_TITLES.length; i++) {
            functionModelList.add(new FunctionModel(i + 1, HOME_FUNCTION_TITLES[i], HOME_FENCTION_ICONS[i]));
        }
        view.updateFunction(functionModelList);
    }

    @Override
    public void loadMoreUnionData() {
        NetCallback<PageModel<UnionModel>> callback = new NetCallback<PageModel<UnionModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<UnionModel>> netResponse) {

                if (netResponse.isSuccess && netResponse.data != null) {
                    view.updateMoreUnionData(netResponse.data.dataList);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                super.onFailure(call, t);

            }
        };
        UniconApi.getUnionStoreList("", page++, pageSize, callback);
    }
}
