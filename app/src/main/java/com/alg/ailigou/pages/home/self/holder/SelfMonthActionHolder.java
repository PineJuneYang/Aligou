package com.alg.ailigou.pages.home.self.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.self.holder
 * Created by Chris Chen on 2017/7/14 13:48.
 * Explain:本月活动
 */

public class SelfMonthActionHolder extends BaseRecyclerHolder {
    @BindView(R.id.iv_home_self_month_action_commodity_image)
    public ImageView image;
    @BindView(R.id.tv_home_self_month_action_commodity_price)
    public TextView price;
    @BindView(R.id.tv_home_self_month_action_commodity_pay)
    public TextView pay;

    public SelfMonthActionHolder(View itemView) {
        super(itemView);
    }
}
