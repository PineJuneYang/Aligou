package com.alg.ailigou.pages.home.entrance.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.pages.home.entrance.holder.HalfPriceHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.entrance.adapter
 * Created by Chris Chen on 2017/7/10 17:00.
 * Explain:今日半价
 */

public class HalfPriceAdapter extends BaseRecyclerAdapter<List<CommodityModel>, CommodityModel, HalfPriceHolder> {
    public HalfPriceAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_half_price;
    }

    @Override
    protected HalfPriceHolder createViewHolder(View itemView) {
        return new HalfPriceHolder(itemView);
    }

    @Override
    protected void refreshView(HalfPriceHolder holder, int position) {
        CommodityModel item = getItem(position);
        ImageLoadUtils.load(mContext, item.imageUrl, holder.image);
        holder.title.setText(item.title);
        holder.price.setText(item.price+"元");
    }
}
