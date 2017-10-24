package com.alg.ailigou.pages.home.self.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.pages.home.self.holder.SelfMonthActionHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.self.adapter
 * Created by Chris Chen on 2017/7/14 15:41.
 * Explain:
 */

public class SelfMonthActionAdapter extends BaseRecyclerAdapter<List<CommodityModel>, CommodityModel, SelfMonthActionHolder> {
    public SelfMonthActionAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_self_month_action;
    }

    @Override
    protected SelfMonthActionHolder createViewHolder(View itemView) {
        return new SelfMonthActionHolder(itemView);
    }

    @Override
    protected void refreshView(SelfMonthActionHolder holder, int position) {
        CommodityModel item = getItem(position);
        ImageUtils.load(mContext, item.imageUrl, holder.image);
        holder.price.setText(item.price + "元");
    }
}
