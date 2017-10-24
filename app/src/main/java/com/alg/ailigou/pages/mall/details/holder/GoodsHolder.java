package com.alg.ailigou.pages.mall.details.holder;

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

public class GoodsHolder extends BaseRecyclerHolder {
    @BindView(R.id.iv_mall_goods_image)
    public ImageView image;
    @BindView(R.id.iv_mall_goods_title)
    public TextView title;
    @BindView(R.id.iv_mall_goods_price)
    public TextView price;

    public GoodsHolder(View itemView) {
        super(itemView);
    }
}
