package com.alg.ailigou.pages.mall.brand;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.brand
 * Created by Chris Chen on 2017/7/21 15:52.
 * Explain:某品牌
 */

public class BrandPresenter implements BrandContracts.Presenter {
    private BrandContracts.View view;

    @Inject
    public BrandPresenter() {
    }

    @Override
    public void bindView(BrandContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }
}
