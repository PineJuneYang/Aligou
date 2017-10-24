package com.alg.ailigou.pages.home.entrance.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.entrance.holder
 * Created by Chris Chen on 2017/7/6 13:05.
 * Explain:
 */

public class FunctionHolder extends BaseRecyclerHolder {
    @BindView(R.id.iv_home_function_icon)
    public ImageView icon;
    @BindView(R.id.tv_home_function_title)
    public TextView title;

    public FunctionHolder(View itemView) {
        super(itemView);
    }
}
