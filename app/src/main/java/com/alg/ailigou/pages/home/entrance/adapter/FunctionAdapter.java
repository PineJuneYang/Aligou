package com.alg.ailigou.pages.home.entrance.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.FunctionModel;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.pages.home.entrance.holder.FunctionHolder;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.entrance.adapter
 * Created by Chris Chen on 2017/7/6 13:04.
 * Explain:功能列表适配器
 */

public class FunctionAdapter extends BaseRecyclerAdapter<List<FunctionModel>, FunctionModel, FunctionHolder> {
    public FunctionAdapter(Context mContext, List<FunctionModel> mData) {
        super(mContext, mData);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_home_function;
    }

    @Override
    protected FunctionHolder createViewHolder(View itemView) {
        return new FunctionHolder(itemView);
    }

    @Override
    protected void refreshView(FunctionHolder holder, int position) {
        FunctionModel item = getItem(position);
        holder.icon.setImageResource(item.iconRes);
        holder.title.setText(item.title);
    }
}
