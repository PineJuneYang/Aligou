package com.alg.ailigou.pages.home.self.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.pages.home.self.holder.SelfHotHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.self.adapter
 * Created by Chris Chen on 2017/7/14 15:39.
 * Explain:自营热销商品
 */

public class SelfHotAdapter extends BaseRecyclerAdapter<List<CommodityModel>, CommodityModel, SelfHotHolder> {
    public SelfHotAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_self_hot;
    }

    @Override
    protected SelfHotHolder createViewHolder(View itemView) {
        return new SelfHotHolder(itemView);
    }

    @Override
    protected void refreshView(SelfHotHolder holder, int position) {
        CommodityModel item = getItem(position);
        ImageUtils.load(mContext, item.imageUrl, holder.image);
    }
}
