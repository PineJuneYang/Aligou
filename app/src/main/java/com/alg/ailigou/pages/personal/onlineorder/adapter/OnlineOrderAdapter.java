package com.alg.ailigou.pages.personal.onlineorder.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.onlineorder.holder.OnLineOrderHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/16
 * 此类或接口用于
 */

public class OnlineOrderAdapter extends BaseHeadAndFootAdapter {



    private LayoutInflater inflater;
    private List<OrderDetailsDataModel> orderDetailsDataModels = new ArrayList<>();

    public OnlineOrderAdapter(List datas, Context context) {
        super(datas, context);

        inflater = LayoutInflater.from(context);
        this.orderDetailsDataModels = datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.alg_item_personal_online_order, parent, false);
        return new OnLineOrderHolder(view, listener, getOnClickListener(), mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OnLineOrderHolder) {
            OnLineOrderHolder onLineOrderHolder = (OnLineOrderHolder) holder;
            onLineOrderHolder.setData(orderDetailsDataModels.get(position));
        }
    }


}
