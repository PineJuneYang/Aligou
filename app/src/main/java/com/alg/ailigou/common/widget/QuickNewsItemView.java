package com.alg.ailigou.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AiligouApp
 * com.alg.ailigou.common.widget
 * Created by Chris Chen on 2017/7/10 13:37.
 * Explain:
 */

public class QuickNewsItemView extends LinearLayout {
    @BindView(R.id.tv_quick_news_item_first)
    TextView mTvItemFirst;
    @BindView(R.id.tv_quick_news_item_second)
    TextView mTvItemSecond;
    private String firstTitle, secondTitle;

    public QuickNewsItemView(Context context) {
        super(context);
        intView();
    }

    public QuickNewsItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intView();
    }

    /**
     * 初始化
     */
    private void intView() {
        inflate(getContext(), R.layout.alg_widget_quick_news_view, this);
        ButterKnife.bind(this);
    }

    public String getFirstTitle() {
        return firstTitle;
    }

    public QuickNewsItemView setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
        mTvItemFirst.setText(firstTitle);
        return this;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public QuickNewsItemView setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
        mTvItemSecond.setText(secondTitle);
        return this;
    }
}
