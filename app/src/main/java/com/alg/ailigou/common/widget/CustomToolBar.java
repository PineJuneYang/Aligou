package com.alg.ailigou.common.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AiligouApp
 * com.alg.ailigou.common.widget
 * Created by Chris Chen on 2017/7/13 14:35.
 * Explain:自定义工具条，减少重复
 */

public class CustomToolBar extends LinearLayout {
    @BindView(R.id.iv_common_toolbar_back)
    ImageView ivCommonToolbarBack;
    @BindView(R.id.tv_common_toolbar_title)
    TextView tvCommonToolbarTitle;
    @BindView(R.id.iv_common_toolbar_right)
    ImageView ivCommonToolbarRight;
    @BindView(R.id.tb_common_toolbar)
    Toolbar tbCommonToolbar;

    private Activity activity;

    public CustomToolBar(Context context) {
        super(context);
        initView();
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.alg_inc_common_toolbar, this);
        ButterKnife.bind(this);
    }

    public void setTitle(CharSequence title) {
        tvCommonToolbarTitle.setText(title);
    }

    public void setBackIcon(@DrawableRes int iconId) {
        initBackButton(iconId);
    }

    private void initBackButton(@DrawableRes int iconId) {
        ivCommonToolbarBack.setImageResource(iconId);
        ivCommonToolbarBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    activity.finish();
                }
            }
        });
    }

    public void setRightIcon(@DrawableRes int iconId) {
        ivCommonToolbarRight.setImageResource(iconId);
    }

    public Toolbar getToolBar() {
        return tbCommonToolbar;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
