package com.alg.ailigou.pages.home.entrance.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.pages.home.entrance.holder.HomeLimitHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.entrance.adapter
 * Created by Chris Chen on 2017/7/24 14:44.
 * Explain:首页限时抢购
 */

public class HomeLimitAdapter extends BaseRecyclerAdapter<List<CommodityModel>,CommodityModel,HomeLimitHolder> {
    public HomeLimitAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_limit_goods;
    }

    @Override
    protected HomeLimitHolder createViewHolder(View itemView) {
        return new HomeLimitHolder(itemView);
    }

    @Override
    protected void refreshView(HomeLimitHolder holder, int position) {
        CommodityModel item = getItem(position);
        ImageLoadUtils.load(mContext,item.imageUrl,holder.image);
        holder.title.setText(item.title);
        holder.price.setText("￥"+item.price);
        holder.oldPrice.setText("￥"+item.oldPrice);
    }
}
