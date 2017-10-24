package com.alg.ailigou.pages.home.self.holder;

import android.view.View;
import android.widget.ImageView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.self.holder
 * Created by Chris Chen on 2017/7/14 15:23.
 * Explain:热销商品
 */

public class SelfHotHolder extends BaseRecyclerHolder{
    @BindView(R.id.iv_home_self_hot_image)
    public ImageView image;
    public SelfHotHolder(View itemView) {
        super(itemView);
    }
}
