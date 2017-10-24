package com.alg.ailigou.pages.personal.moneychange;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.CommonTypeModel;
import com.alg.ailigou.common.model.MoneyChangeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/4
 * 此类或接口用于
 */

public class MoneyChangePresenter implements MoneyChangeContracts.Presenter {


    private MoneyChangeContracts.View view;

    @Inject
    public MoneyChangePresenter() {
    }


    @Override
    public void bindView(MoneyChangeContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    //    public long startTime;
//    public long endTime;
//    public int type;//处理状态
    @Override
    public void loadData(int page, int pageSize, long startTime, long endTime, int type) {
        UserApi.getMoneyExchangeList(page, pageSize, startTime, endTime, type, new NetCallback<PageModel<MoneyChangeModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<MoneyChangeModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.notify(netResponse.data);
                }
            }
        });
    }


    private List<String> status;

    @Override
    public void getStatuList() {
        view.showLoading();
        UserApi.getMoneyExchangeTypeList(new NetCallback<List<CommonTypeModel>>() {
            @Override
            protected void onComplete(NetResponse<List<CommonTypeModel>> netResponse) {
                view.dismissLoading();
                if (netResponse.isSuccess) {
                    view.showStatuPicker(netResponse.data);
                }
            }
        });

    }
}
