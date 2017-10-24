package com.alg.ailigou.pages.personal.myorder.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.myorder.holder.AllViewHolder;
import com.alg.ailigou.pages.personal.myorder.holder.PreReceiveViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 玖泞
 * on 2017/8/9
 * 此类或接口用于
 */

public class PreReceiveFragmentAdapter extends BaseHeadAndFootAdapter {
    private int position;


    private List<OrderDetailsDataModel> commodityModels = new ArrayList<>();

    public PreReceiveFragmentAdapter(List datas, Context context, int position) {
        super(datas, context);
        commodityModels = datas;
        this.position = position;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.alg_item_personal_my_order, parent, false);
        return new PreReceiveViewHolder(inflate, listener, getOnClickListener(), position,mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PreReceiveViewHolder) {
            PreReceiveViewHolder preReceiveViewHolder = (PreReceiveViewHolder) holder;
            preReceiveViewHolder.setData(commodityModels.get(position));
        }
    }
}
