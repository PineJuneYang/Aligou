package com.alg.ailigou.common.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * AiligouApp
 * com.alg.ailigou.common.base
 * Created by Chris Chen on 2017/8/14 10:24.
 * Explain:分离页面UI持有代码
 */

public class BaseUiViewHolder {
    public BaseUiViewHolder(View contentView) {
        ButterKnife.bind(this, contentView);
    }
}
