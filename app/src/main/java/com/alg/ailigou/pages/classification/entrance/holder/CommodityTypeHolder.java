package com.alg.ailigou.pages.classification.entrance.holder;

import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.classification.entrance.holder
 * Created by Chris Chen on 2017/7/7 16:58.
 * Explain:商品分类holder
 */

public class CommodityTypeHolder extends BaseRecyclerHolder{
    @BindView(R.id.tv_commodity_type_title)
    public TextView title;

    public CommodityTypeHolder(View itemView) {
        super(itemView);
    }
}
