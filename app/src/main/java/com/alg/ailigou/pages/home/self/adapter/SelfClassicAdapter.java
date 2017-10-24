package com.alg.ailigou.pages.home.self.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.HomeCommodityTypeModel;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.pages.home.self.holder.SelfClassicHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.self.adapter
 * Created by Chris Chen on 2017/7/14 15:35.
 * Explain:自营分类
 */

public class SelfClassicAdapter extends BaseRecyclerAdapter<List<HomeCommodityTypeModel>, HomeCommodityTypeModel, SelfClassicHolder> {
    public SelfClassicAdapter(Context mContext) {
        super(mContext, null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_self_classic;
    }

    @Override
    protected SelfClassicHolder createViewHolder(View itemView) {
        return new SelfClassicHolder(itemView);
    }

    @Override
    protected void refreshView(SelfClassicHolder holder, int position) {
        HomeCommodityTypeModel item = getItem(position);
        ImageUtils.load(mContext, item.imageUrl, holder.image);
        holder.title.setText(item.title);
    }
}
