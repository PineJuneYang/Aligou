package com.alg.ailigou.pages.mall.entrance.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.entrance.holder
 * Created by Chris Chen on 2017/7/21 18:01.
 * Explain:简单商品分类
 */

public class SimpleCommodityTypeHolder extends BaseRecyclerHolder{
    @BindView(R.id.iv_home_discount_image)
    public ImageView image;
    @BindView(R.id.tv_home_discount_title)
    public TextView title;

    public SimpleCommodityTypeHolder(View itemView) {
        super(itemView);
    }
}
