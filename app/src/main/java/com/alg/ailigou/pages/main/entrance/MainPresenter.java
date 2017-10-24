package com.alg.ailigou.pages.main.entrance;

import javax.inject.Inject;

/**
 * com.alg.ailigouapp.pages.main.entrance
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 12:01
 * Explain:
 */

public class MainPresenter implements MainContracts.Presenter {
    private MainContracts.View view;

    /**
     * 在presenter的构造方法上加上注入器注解，可以自动调用此方法新建对象
     */
    @Inject
    public MainPresenter() {
    }

    @Override
    public void bindView(MainContracts.View view) {
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
