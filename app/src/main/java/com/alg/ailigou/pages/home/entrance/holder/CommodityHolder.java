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
 * Created by Chris Chen on 2017/7/6 17:50.
 * Explain:商品holder
 */

public class CommodityHolder extends BaseRecyclerHolder {
    @BindView(R.id.iv_commodity_zone_second_image)
    public ImageView image;
    @BindView(R.id.tv_commodity_zone_second_title)
    public TextView title;

    public CommodityHolder(View itemView) {
        super(itemView);
    }
}
