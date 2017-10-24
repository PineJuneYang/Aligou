package com.alg.ailigou.pages.mall.advice;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.advice
 * Created by Chris Chen on 2017/7/21 16:05.
 * Explain:在线咨询
 */

public class AdvicePresenter implements AdviceContracts.Presenter {
    private AdviceContracts.View view;

    @Inject
    public AdvicePresenter() {
    }

    @Override
    public void bindView(AdviceContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }
}
