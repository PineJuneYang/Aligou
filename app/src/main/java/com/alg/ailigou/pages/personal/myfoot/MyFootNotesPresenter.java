package com.alg.ailigou.pages.personal.myfoot;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public class MyFootNotesPresenter implements MyFootNotesContracts.Presenter {

    private MyFootNotesContracts.View view;

    @Inject
    public MyFootNotesPresenter() {

    }


    @Override
    public void bindView(MyFootNotesContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void deleteAllData() {
        view.setViewForEmpty();

    }

    @Override
    public void getListData(int page, int pageSize) {
        UserApi.getFootprintsList(page, pageSize, new NetCallback<PageModel<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityModel>> netResponse) {
                if (netResponse.isSuccess) {
                    if (netResponse.data.dataList==null||netResponse.data.dataList.size()==0){
                        view.setViewForEmpty();
                    }else {
                        view.notify(netResponse.data);
                    }
                }
            }
        });
    }
}
