package com.alg.ailigou.pages.home.entrance.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.pages.home.entrance.holder.CommodityHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.entrance.adapter
 * Created by Chris Chen on 2017/7/6 17:52.
 * Explain:
 */

public class CommodityAdapter extends BaseRecyclerAdapter<List<CommodityModel>, CommodityModel, CommodityHolder> {

    public CommodityAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_recommend_second;
    }

    @Override
    protected CommodityHolder createViewHolder(View itemView) {
        return new CommodityHolder(itemView);
    }

    @Override
    protected void refreshView(CommodityHolder holder, int position) {
        CommodityModel item = getItem(position);
        ImageLoadUtils.load(mContext, item.imageUrl, holder.image);
        holder.title.setText(item.title);
    }
}
