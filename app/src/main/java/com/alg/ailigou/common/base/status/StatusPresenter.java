package com.alg.ailigou.common.base.status;

import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.common.widget.StatusLayout;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.NetworkUtils;

/**
 * AiligouApp
 * com.alg.ailigou.common.base
 * Created by Chris Chen on 2017/7/18 14:01.
 * Explain:处理网络请求状态的基类
 */

public abstract class StatusPresenter<DataType,View extends StatusView> implements BasePresenter<View> {
    protected View view;

    @Override
    public void bindView(View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    /**
     * 处理网络请求结果
     *
     * @param netResponse
     */
    public void handleResponse(NetResponse<DataType> netResponse) {
        if (netResponse.isSuccess) {
            if (netResponse.data != null) {
                handleSuccess(netResponse);//有数据
            } else {
                view.setStatus(StatusLayout.EMPTY);//数据为空
            }
        } else {
            if (!NetworkUtils.isNetworkConnected()) {
                view.setStatus(StatusLayout.ERROR_NET);//网络错误
            } else {
                view.setStatus(StatusLayout.ERROR_DATA);//请求数据异常
            }
        }
    }

    /**
     * 请求成功的处理
     *
     * @param netResponse
     */
    protected abstract void handleSuccess(NetResponse<DataType> netResponse);
}
