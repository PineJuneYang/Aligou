package com.alg.ailigou.library.widget;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * AiligouApp
 * com.alg.ailigou.library.widget
 * Created by Chris Chen on 2017/7/11 17:03.
 * Explain:泡泡窗口
 */

public abstract class BasePopupWindow extends PopupWindow {
    private View contentView;

    public BasePopupWindow(Context context) {
        super(context);
        initView();
    }

    private void initView() {
    }
}
