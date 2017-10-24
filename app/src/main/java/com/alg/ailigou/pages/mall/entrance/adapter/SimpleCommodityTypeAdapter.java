package com.alg.ailigou.pages.mall.entrance.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.HomeCommodityTypeModel;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.pages.mall.entrance.holder.SimpleCommodityTypeHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.entrance.adapter
 * Created by Chris Chen on 2017/7/21 18:01.
 * Explain:简单商品分类
 */

public class SimpleCommodityTypeAdapter extends BaseRecyclerAdapter<List<HomeCommodityTypeModel>, HomeCommodityTypeModel, SimpleCommodityTypeHolder> {
    public SimpleCommodityTypeAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_discount;
    }

    @Override
    protected SimpleCommodityTypeHolder createViewHolder(View itemView) {
        return new SimpleCommodityTypeHolder(itemView);
    }

    @Override
    protected void refreshView(SimpleCommodityTypeHolder holder, int position) {
        HomeCommodityTypeModel item = getItem(position);
        ImageUtils.load(mContext, item.imageUrl, holder.image);
        holder.title.setText(item.title);
    }
}
