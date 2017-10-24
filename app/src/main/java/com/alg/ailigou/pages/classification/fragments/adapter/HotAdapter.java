package com.alg.ailigou.pages.classification.fragments.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.pages.classification.fragments.holder.HotHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.classification.fragments.adapter
 * Created by Chris Chen on 2017/7/11 14:32.
 * Explain:热卖推荐
 */

public class HotAdapter extends BaseRecyclerAdapter<List<CommodityTypeModel>, CommodityTypeModel, HotHolder> {

    public HotAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_classification_hot;
    }

    @Override
    protected HotHolder createViewHolder(View itemView) {
        return new HotHolder(itemView);
    }

    @Override
    protected void refreshView(HotHolder holder, int position) {
        CommodityTypeModel item = getItem(position);
        ImageLoadUtils.load(mContext, item.imageUrl, holder.image);
        holder.title.setText(item.title);
    }
}
