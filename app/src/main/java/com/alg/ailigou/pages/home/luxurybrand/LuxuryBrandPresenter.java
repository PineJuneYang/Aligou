package com.alg.ailigou.pages.home.luxurybrand;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/7/20.
 * 此类或接口用于
 */

public class LuxuryBrandPresenter implements LuxuryBrandContracts.Presenter {
    private LuxuryBrandContracts.View view;

    @Inject
    public LuxuryBrandPresenter() {
    }

    @Override
    public void bindView(LuxuryBrandContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadTopData() {

    }

    @Override
    public void loadListData() {

    }
}
