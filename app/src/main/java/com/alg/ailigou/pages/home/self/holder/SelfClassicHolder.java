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
 * Created by Chris Chen on 2017/7/14 13:44.
 * Explain:自营分类
 */

public class SelfClassicHolder extends BaseRecyclerHolder {
    @BindView(R.id.iv_self_classic_image)
    public ImageView image;
    @BindView(R.id.tv_self_classic_title)
    public TextView title;

    public SelfClassicHolder(View itemView) {
        super(itemView);
    }
}
