package com.alg.ailigou.pages.personal.offlineorder.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.OrderOffLineDataModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.offlineorder.holder.OffLineOrderHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/8/16
 * 此类或接口用于
 */

public class OffLineOrderAdapter extends BaseHeadAndFootAdapter {



    private LayoutInflater inflater;

    private List<OrderOffLineDataModel> orderOffLineDataModels = new ArrayList<>();

    public OffLineOrderAdapter(List datas, Context context) {
        super(datas, context);

        inflater = LayoutInflater.from(context);
        this.orderOffLineDataModels = datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.alg_item_personal_offline_order, parent, false);
        return new OffLineOrderHolder(view, listener, getOnClickListener(), mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OffLineOrderHolder) {
            OffLineOrderHolder offLineOrderHolder = (OffLineOrderHolder) holder;
            offLineOrderHolder.setData(orderOffLineDataModels.get(position));
        }
    }


}
