package com.alg.ailigou.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.alg.ailigou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 网络请求进行时的页面控制
 */
public class StatusLayout extends FrameLayout {

    public static final int LOADING = 1;//加载中
    public static final int EMPTY = 2;//空数据
    public static final int SUCCESS = 3;//加载成功
    public static final int ERROR_NET = 4;//出错了（网络问题）
    public static final int ERROR_DATA = 5;//出错了（数据问题）

    public int currentState = LOADING;//当前状态

    @BindView(R.id.ll_loading)
    LinearLayout mLlLoading;
    @BindView(R.id.ll_nonetwork)
    LinearLayout mLlNonetwork;
    @BindView(R.id.ll_empty)
    LinearLayout mLlEmpty;
    @BindView(R.id.ll_error)
    LinearLayout mLlError;


    private View successView;

    public StatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.alg_widget_statuslayout, this);
        ButterKnife.bind(this);
    }

    public void showStatus(int state) {
        currentState = state;
        if (getChildCount() != 2) {
            throw new RuntimeException("StatusLayout只能有一个子View");
        }
        if (successView == null) {
            successView = getChildAt(1);
        }
        switch (state) {
            case LOADING:
                mLlLoading.setVisibility(View.VISIBLE);
                mLlNonetwork.setVisibility(View.GONE);
                mLlEmpty.setVisibility(View.GONE);
                successView.setVisibility(View.GONE);
                mLlError.setVisibility(View.GONE);
                break;
            case EMPTY:
                mLlLoading.setVisibility(View.GONE);
                mLlNonetwork.setVisibility(View.GONE);
                mLlEmpty.setVisibility(View.VISIBLE);
                successView.setVisibility(View.GONE);
                mLlError.setVisibility(View.GONE);
                break;
            case SUCCESS:
                mLlLoading.setVisibility(View.GONE);
                mLlNonetwork.setVisibility(View.GONE);
                mLlEmpty.setVisibility(View.GONE);
                successView.setVisibility(View.VISIBLE);
                mLlError.setVisibility(View.GONE);
                break;
            case ERROR_NET:
                mLlLoading.setVisibility(View.GONE);
                mLlNonetwork.setVisibility(View.VISIBLE);
                mLlEmpty.setVisibility(View.GONE);
                successView.setVisibility(View.GONE);
                mLlError.setVisibility(View.GONE);
                break;
            case ERROR_DATA:
                mLlLoading.setVisibility(View.GONE);
                mLlNonetwork.setVisibility(View.GONE);
                mLlEmpty.setVisibility(View.GONE);
                successView.setVisibility(View.GONE);
                mLlError.setVisibility(View.VISIBLE);
                break;
        }
    }


    @OnClick(R.id.btn_reload)
    public void onClick() {
        if (mOnReloadListener != null) {
            mOnReloadListener.onReload();
        }
    }

    private OnReloadListener mOnReloadListener;

    public interface OnReloadListener {
        void onReload();
    }

    public void setOnReloadListener(OnReloadListener mOnReloadListener) {
        this.mOnReloadListener = mOnReloadListener;
    }

}
