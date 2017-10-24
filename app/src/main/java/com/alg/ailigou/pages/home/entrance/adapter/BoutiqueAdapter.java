package com.alg.ailigou.pages.home.entrance.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.pages.home.entrance.holder.BoutiqueHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.entrance.adapter
 * Created by Chris Chen on 2017/7/10 17:02.
 * Explain:精品推荐
 */

public class BoutiqueAdapter extends BaseRecyclerAdapter<List<CommodityModel>, CommodityModel, BoutiqueHolder> {
    public BoutiqueAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_boutique;
    }

    @Override
    protected BoutiqueHolder createViewHolder(View itemView) {
        return new BoutiqueHolder(itemView);
    }

    @Override
    protected void refreshView(BoutiqueHolder holder, int position) {
        CommodityModel item = getItem(position);
//        ImageLoadUtils.load(mContext, item.imageUrl, holder.image);
        ImageLoadUtils.loadSquareImage(mContext, item.imageUrl, holder.image,2,10);
        holder.title.setText(item.title);
        holder.price.setText("￥"+item.price);
    }
}
