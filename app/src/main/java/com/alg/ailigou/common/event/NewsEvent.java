package com.alg.ailigou.common.event;

import com.alg.ailigou.library.base.event.BaseEvent;

/**
 * AiligouApp
 * com.alg.ailigou.common.event
 * Created by Chris Chen on 2017/7/13 17:26.
 * Explain:点击爱利购快讯之后，通知activity加载第三个fragment
 */

public class NewsEvent extends BaseEvent<String> {
    public NewsEvent(int code) {
        this.code = code;
    }
}
