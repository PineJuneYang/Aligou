package com.alg.ailigou.pages.personal.seelogistics.adapter;

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
import com.alg.ailigou.pages.personal.consts.PersonalConsts;
import com.alg.ailigou.pages.personal.seelogistics.holder.OrderGoodsHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 海航
 * on 2017/8/9.
 * 此类或接口用于
 */

public class OrderGoodsAdapter extends BaseHeadAndFootAdapter {


    private List<CommodityModel> datas;

    public OrderGoodsAdapter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;
        this.type= PersonalConsts.ORDER_TYPE_BUY;
    }
    private int type;
    public OrderGoodsAdapter(List datas, Context context,int type) {
        super(datas, context);
        this.datas = datas;
        this.type=type;
    }
    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_personal_order_goods, parent, false);
        return new OrderGoodsHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  OrderGoodsHolder){
            OrderGoodsHolder vh = (OrderGoodsHolder) holder;
            Glide.with(mContext).load(datas.get(position).imageUrl).into(vh.getIvGoods());
            vh.getTvGoodsCount().setText(datas.get(position).buyCount + "");
            vh.getTvGoodsName().setText(datas.get(position).title);
            vh.getTvGoodsPrice().setText("价格:￥" + datas.get(position).price);
            vh.getTvGoodsDesc().setText(datas.get(position).describe);
            if (type==PersonalConsts.ORDER_TYPE_BUY){
                vh.getTvLigouCount().setVisibility(View.GONE);
            }else {
                vh.getTvLigouCount().setVisibility(View.VISIBLE);
                vh.getTvLigouCount().setText("利购券:￥"+datas.get(position).cheapTicketCount);
            }
        }

    }
}
