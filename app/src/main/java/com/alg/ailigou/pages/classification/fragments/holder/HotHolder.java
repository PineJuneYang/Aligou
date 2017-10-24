package com.alg.ailigou.pages.classification.fragments.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.classification.fragments.holder
 * Created by Chris Chen on 2017/7/11 14:30.
 * Explain:热卖推荐
 */

public class HotHolder extends BaseRecyclerHolder{
    @BindView(R.id.iv_hot_image)
    public ImageView image;
    @BindView(R.id.tv_hot_title)
    public TextView title;

    public HotHolder(View itemView) {
        super(itemView);
    }
}
