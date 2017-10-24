package com.alg.ailigou.pages.supper.entrance;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.news.entrance
 * Created by Chris Chen on 2017/7/4 17:32.
 * Explain:
 */

public class SupperPresenter implements SupperContracts.Presenter {
    private SupperContracts.View view;

    @Inject
    public SupperPresenter() {
    }

    @Override
    public void bindView(SupperContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }
}
