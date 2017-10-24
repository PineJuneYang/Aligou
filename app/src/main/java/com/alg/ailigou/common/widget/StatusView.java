package com.alg.ailigou.common.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.alg.ailigou.R;
import com.alg.ailigou.library.widget.BaseStatusView;

/**
 * AiligouApp
 * com.alg.ailigou.common.widget
 * Created by Chris Chen on 2017/7/18 11:58.
 * Explain:网络请求状态
 */

public class StatusView extends BaseStatusView {
    public StatusView(@NonNull Context context) {
        super(context);
    }

    public StatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int[] setLayouts() {
        int[] layoutIds = {R.layout.alg_widget_status_loading,
                R.layout.alg_widget_status_empty,
                R.layout.alg_widget_status_net_error,
                R.layout.alg_widget_status_data_error,
                R.id.btn_reload};
        return layoutIds;
    }
}
