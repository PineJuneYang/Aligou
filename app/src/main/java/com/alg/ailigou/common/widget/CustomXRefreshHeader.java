package com.alg.ailigou.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.alg.ailigou.R;
import com.andview.refreshview.callback.IHeaderCallBack;

/**
 * AiligouApp
 * com.alg.ailigou.common.widget
 * Created by Chris Chen on 2017/7/17 13:12.
 * Explain:
 */

public class CustomXRefreshHeader extends LinearLayout implements IHeaderCallBack {
    public CustomXRefreshHeader(Context context) {
        super(context);
        initView();
    }

    public CustomXRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.alg_widget_xrefresh_view_header, this);
    }

    @Override
    public void onStateNormal() {

    }

    @Override
    public void onStateReady() {

    }

    @Override
    public void onStateRefreshing() {

    }

    @Override
    public void onStateFinish(boolean success) {

    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {

    }

    @Override
    public void setRefreshTime(long lastRefreshTime) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public int getHeaderHeight() {
        return getMeasuredHeight();
    }
}
