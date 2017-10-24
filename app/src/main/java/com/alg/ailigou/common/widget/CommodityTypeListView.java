package com.alg.ailigou.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.alg.ailigou.R;

/**
 * AiligouApp
 * com.alg.ailigou.common.widget
 * Created by Chris Chen on 2017/7/10 09:29.
 * Explain:
 */

public class CommodityTypeListView extends ScrollView {

    public CommodityTypeListView(Context context) {
        super(context, null);
    }

    public CommodityTypeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.alg_widget_commodity_type_view, this);
    }
}
