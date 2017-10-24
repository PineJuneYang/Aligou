package com.alg.ailigou.pages.home.entrance.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.pages.home.entrance.holder.DiscountHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.entrance.adapter
 * Created by Chris Chen on 2017/7/10 16:11.
 * Explain:独家享优惠适配器
 */

public class DiscountAdapter extends BaseRecyclerAdapter<List<CommodityModel>, CommodityModel, DiscountHolder> {
    public DiscountAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_discount;
    }

    @Override
    protected DiscountHolder createViewHolder(View itemView) {
        return new DiscountHolder(itemView);
    }

    @Override
    protected void refreshView(DiscountHolder holder, int position) {
        CommodityModel item = getItem(position);
        ImageLoadUtils.load(mContext, item.imageUrl, holder.image);
        holder.title.setText(item.title);
    }
}
