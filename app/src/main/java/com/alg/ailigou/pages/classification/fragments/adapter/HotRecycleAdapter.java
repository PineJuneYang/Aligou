package com.alg.ailigou.pages.classification.fragments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.classification.fragments.holder.HotHeadHolder;
import com.alg.ailigou.pages.classification.fragments.holder.HotRecycleHolder;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/9/7
 * 此类或接口用于
 */

public class HotRecycleAdapter extends BaseHeadAndFootAdapter {


    public HotRecycleAdapter(List datas, Context context) {
        super(datas, context);

    }

    @Override
    protected MyBaseViewHolder onHeadViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = (View) mHeaderViews.get(BASE_ITEM_TYPE_HEADER);
        return new HotHeadHolder(view, null);
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.alg_item_classification_hot, parent, false);
        return new HotRecycleHolder(inflate, listener, mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HotRecycleHolder) {
            HotRecycleHolder hotRecycleHolder = (HotRecycleHolder) holder;
            CommodityTypeModel commodityTypeModel = (CommodityTypeModel) datas.get(position - 1);
            hotRecycleHolder.setData(commodityTypeModel);
        } else if (holder instanceof HotHeadHolder) {

        }
    }
}
