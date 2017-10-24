package com.alg.ailigou.pages.home.entrance.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.entrance.holder
 * Created by Chris Chen on 2017/7/10 16:09.
 * Explain:独家享优惠
 */

public class DiscountHolder extends BaseRecyclerHolder {
    @BindView(R.id.tv_home_discount_title)
    public TextView title;
    @BindView(R.id.iv_home_discount_image)
    public ImageView image;

    public DiscountHolder(View itemView) {
        super(itemView);
    }
}
