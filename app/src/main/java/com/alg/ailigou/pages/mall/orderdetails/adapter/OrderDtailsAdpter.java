package com.alg.ailigou.pages.mall.orderdetails.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.mall.orderdetails.holder.OrderDtailsHolder;

import java.util.List;

/**
 * Created by 海航
 * on 2017/8/2.
 * 此类或接口用于
 */

public class OrderDtailsAdpter extends BaseHeadAndFootAdapter {

    private List<CommodityModel> datas;

    public OrderDtailsAdpter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_mall_order_details, parent, false);
        return new OrderDtailsHolder(view, listener, getOnClickListener());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OrderDtailsHolder){
            OrderDtailsHolder vh = (OrderDtailsHolder) holder;
            vh.getTvGoodsDesc().setText(datas.get(position).describe);
            vh.getTvGoodsCount().setText("" + datas.get(position).buyCount);
            vh.getTvGoodsPrice().setText("价格:" + datas.get(position).price);
            vh.getTvGoodsName().setText(datas.get(position).title);
            vh.getTvEditGoodsCount().setText("" + datas.get(position).buyCount);
        }

    }


}
