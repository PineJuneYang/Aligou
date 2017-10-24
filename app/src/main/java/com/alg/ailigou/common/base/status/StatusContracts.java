package com.alg.ailigou.common.base.status;

import com.alg.ailigou.library.base.mvp.BasePresenter;

/**
 * AiligouApp
 * com.alg.ailigou.common.base.status
 * Created by Chris Chen on 2017/7/18 14:09.
 * Explain:网络请求状态处理的契约xx
 */

public interface StatusContracts {
    interface View extends StatusView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}
