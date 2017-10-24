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
 * Created by Chris Chen on 2017/7/10 16:55.
 * Explain:精品推荐
 */

public class BoutiqueHolder extends BaseRecyclerHolder {
    @BindView(R.id.iv_home_boutique_image)
    public ImageView image;
    @BindView(R.id.tv_home_boutique_title)
    public TextView title;
    @BindView(R.id.tv_home_boutique_price)
    public TextView price;

    public BoutiqueHolder(View itemView) {
        super(itemView);
    }
}
