package com.alg.ailigou.common.event;

import com.alg.ailigou.library.base.event.BaseEvent;

/**
 * AiligouApp
 * com.alg.ailigou.common.event
 * Created by Chris Chen on 2017/7/26 10:47.
 * Explain:跳转到联盟商家模块
 */

public class UnionEvent extends BaseEvent {
    public UnionEvent(int code) {
        this.code=code;
    }
}
