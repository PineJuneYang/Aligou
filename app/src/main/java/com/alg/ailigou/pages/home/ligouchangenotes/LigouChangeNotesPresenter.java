package com.alg.ailigou.pages.home.ligouchangenotes;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.LigouExchangeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于 利购券兑换记录
 */

public class LigouChangeNotesPresenter implements LigouChangeNotesContracts.Presenter {
    private LigouChangeNotesContracts.View view;

    @Inject
    public LigouChangeNotesPresenter() {
    }

    @Override
    public void bindView(LigouChangeNotesContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData(long timeFrom, long timeTo, int staus, int page, int pageSize) {
        UserApi.getLigouExchangeList(timeFrom, timeTo, staus, page, pageSize, new NetCallback<PageModel<LigouExchangeModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<LigouExchangeModel>> netResponse) {
                if (netResponse.isSuccess){
                    view.notify(netResponse.data);
                }
            }
        });
    }

    private List<String> status;

    @Override
    public List<String> getStatuList() {
        if (status == null) {
            status = new ArrayList<>();
            status.add("未处理");
            status.add("处理中");
            status.add("已处理");
        }
        return status;

    }
}
