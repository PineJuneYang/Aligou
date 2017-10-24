package com.alg.ailigou.library.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.alg.ailigou.library.utils.MessageUtils;

/**
 * AiligouApp
 * com.alg.ailigou.library.widget
 * Created by Chris Chen on 2017/7/17 16:52.
 * Explain:网络请求状态布局基类
 */

public abstract class BaseStatusView extends FrameLayout {
    public static final int STATE_SUCCESS = 0x00000001;//正在加载成功
    public static final int STATE_LOADING = 0x00000002;//正在加载
    public static final int STATE_EMPTY = 0x00000003;//数据为空
    public static final int STATE_NET_ERROR = 0x00000004;//网络异常
    public static final int STATE_DATA_ERROR = 0x00000005;//数据错误

    public static int state = STATE_LOADING;//当前状态
    protected View successView, loadingView, emptyView, netErrorView, dataErrorView, reloadButton;
    protected OnReloadListener onReloadListener;

    public BaseStatusView(@NonNull Context context) {
        super(context);
        initView();
    }

    public BaseStatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        int[] layoutIds = setLayouts();
        if (layoutIds.length == 5) {
            loadingView = LayoutInflater.from(getContext()).inflate(layoutIds[0], null);
            emptyView = LayoutInflater.from(getContext()).inflate(layoutIds[1], null);
            netErrorView = LayoutInflater.from(getContext()).inflate(layoutIds[2], null);
            dataErrorView = LayoutInflater.from(getContext()).inflate(layoutIds[3], null);
            addView(loadingView);
            addView(emptyView);
            addView(netErrorView);
            addView(dataErrorView);
            if (netErrorView != null) {
                reloadButton = netErrorView.findViewById(layoutIds[4]);
                reloadButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onReloadListener != null) {
                            onReloadListener.onReload();
                        }
                    }
                });
            }
//            successView=
        }
        MessageUtils.debug(getChildCount());

        loadingView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
        netErrorView.setVisibility(GONE);
        dataErrorView.setVisibility(GONE);
        //successView.setVisibility(GONE);
    }

    /**
     * 数组长度为5，依次是成功、数据为空、网络异常、数据异常和刷新按钮，而刷新按钮一定要在网络异常的布局里面
     */
    public abstract int[] setLayouts();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        BaseStatusView.state = state;
        switch (state) {
            case STATE_SUCCESS://成功
                switchToState(successView);
                break;
            case STATE_LOADING://正在加载
                switchToState(loadingView);
                break;
            case STATE_EMPTY://数据为空
                switchToState(emptyView);
                break;
            case STATE_NET_ERROR://网络错误
                switchToState(netErrorView);
                break;
            case STATE_DATA_ERROR://请求数据错误
                switchToState(dataErrorView);
                break;
            default:
                break;
        }
    }

    private void switchToState(View view) {
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        netErrorView.setVisibility(GONE);
        dataErrorView.setVisibility(GONE);

        view.setVisibility(VISIBLE);
    }

    public void setOnReloadListener(OnReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }

    public interface OnReloadListener {
        void onReload();
    }

}
