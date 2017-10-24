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
 * Created by Chris Chen on 2017/7/24 14:35.
 * Explain:限时抢购
 */

public class HomeLimitHolder extends BaseRecyclerHolder {
    @BindView(R.id.iv_home_limit_goods_image)
    public ImageView image;//图片
    @BindView(R.id.tv_home_limit_goods_title)
    public TextView title;//标题
    @BindView(R.id.tv_home_limit_goods_price)
    public TextView price;//价格
    @BindView(R.id.tv_home_limit_goods_price_old)
    public TextView oldPrice;//原价

    public HomeLimitHolder(View itemView) {
        super(itemView);
    }
}
