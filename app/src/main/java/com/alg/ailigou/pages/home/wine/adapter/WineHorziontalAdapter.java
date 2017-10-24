package com.alg.ailigou.pages.home.wine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.wine.holder.WineHorizontalHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/18.
 * 此类或接口用于  佳酿系列的adapter
 */

public class WineHorziontalAdapter extends BaseHeadAndFootAdapter {
    private List<CommodityTypeModel> goods;

    public WineHorziontalAdapter(List datas, Context context) {
        super(datas, context);
        this.goods = datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {

        return new WineHorizontalHolder(LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_wine_horizontal, parent, false), getListener());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WineHorizontalHolder){
        WineHorizontalHolder viewHolder = (WineHorizontalHolder) holder;
        viewHolder.getTvGoodsName().setText(goods.get(position).title);
        Glide.with(mContext).load(goods.get(position).imageUrl).into(viewHolder.getIvGoods());
    }
    }
}
