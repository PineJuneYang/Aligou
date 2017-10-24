package com.alg.ailigou.pages.home.ligouchangedetails;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于
 */

public class LigouChangeDetailsPresenter implements LigouChangeDetailsContracts.Presenter {
    private LigouChangeDetailsContracts.View view;

    @Inject
    public LigouChangeDetailsPresenter() {
    }

    @Override
    public void bindView(LigouChangeDetailsContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData() {

    }
}
