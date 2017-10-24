package com.alg.ailigou.library.base.fragment;

import com.alg.ailigou.library.base.mvp.BasePresenter;

/**
 * AiligouApp
 * com.alg.ailigouapp.library.base
 * Created by Chris Chen on 2017/7/3 15:21.
 * Explain:
 */

public abstract class BaseMvpFragment extends BaseFragment {
    @Override
    protected void afterContentView() {
        if (getPresenter() != null) {
            getPresenter().bindView(this);
        }
    }

    /**
     * 获取创建好的presenter
     *
     * @return
     */
    protected abstract BasePresenter getPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().unbindView();
        }
    }

    /**
     * 开始加载
     */
    public void showLoading() {

    }


    /**
     * 取消加载
     */
    public void dismissLoading() {

    }


    /**
     * 刷新成功
     */
    public void refreshComplete() {
    }
}
