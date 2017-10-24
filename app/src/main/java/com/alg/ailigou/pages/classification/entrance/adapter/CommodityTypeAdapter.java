package com.alg.ailigou.pages.classification.entrance.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.pages.classification.entrance.holder.CommodityTypeHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.classification.entrance.adapter
 * Created by Chris Chen on 2017/7/7 16:57.
 * Explain:商品分类适配器
 */

public class CommodityTypeAdapter extends BaseRecyclerAdapter<List<CommodityTypeModel>, CommodityTypeModel, CommodityTypeHolder> {

    public CommodityTypeAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_classification_commoditi_type;
    }

    @Override
    protected CommodityTypeHolder createViewHolder(View itemView) {
        return new CommodityTypeHolder(itemView);
    }

    @Override
    protected void refreshView(CommodityTypeHolder holder, int position) {
        CommodityTypeModel item = getItem(position);
        holder.title.setText(item.title.replace(" ","\n"));
    }
}
