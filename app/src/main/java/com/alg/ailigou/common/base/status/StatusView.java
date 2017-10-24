package com.alg.ailigou.common.base.status;

import com.alg.ailigou.library.base.mvp.BaseView;

/**
 * AiligouApp
 * com.alg.ailigou.common.base.status
 * Created by Chris Chen on 2017/7/18 14:09.
 * Explain:网络请求状态处理View
 */

public interface StatusView extends BaseView{
    void setStatus(int state);//设置状态
}
