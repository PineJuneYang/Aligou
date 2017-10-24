package com.alg.ailigou.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * AiligouApp
 * com.alg.ailigouapp.library.widget
 * Created by Chris Chen on 2017/7/4 14:53.
 * Explain:固定高度的ListView
 */

public class CustomListView extends ListView{
    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST));
    }
}
