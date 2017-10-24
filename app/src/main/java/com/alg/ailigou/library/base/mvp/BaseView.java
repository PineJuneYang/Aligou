package com.alg.ailigou.library.base.mvp;

/**
 * com.alg.ailigouapp.library.mvp
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 11:48
 * Explain:MVP-View
 */

public interface BaseView {

    void showLoading();

    void dismissLoading();
    /**
     * 刷新完成
     */
    void refreshComplete();
}
