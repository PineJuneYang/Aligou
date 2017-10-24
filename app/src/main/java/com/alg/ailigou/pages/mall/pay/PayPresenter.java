package com.alg.ailigou.pages.mall.pay;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.pay
 * Created by Chris Chen on 2017/7/21 16:00.
 * Explain:支付
 */

public class PayPresenter implements PayContracts.Presenter {
    private PayContracts.View view;

    @Inject
    public PayPresenter() {
    }

    @Override
    public void bindView(PayContracts.View view) {
        this.view = null;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }
}
