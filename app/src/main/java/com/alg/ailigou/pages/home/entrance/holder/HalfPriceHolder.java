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
 * Created by Chris Chen on 2017/7/10 16:51.
 * Explain:今日半价
 */

public class HalfPriceHolder extends BaseRecyclerHolder {
    @BindView(R.id.iv_home_half_price_image)
    public ImageView image;
    @BindView(R.id.tv_home_half_price_title)
    public TextView title;
    @BindView(R.id.tv_home_half_price_price)
    public TextView price;

    public HalfPriceHolder(View itemView) {
        super(itemView);
    }
}
