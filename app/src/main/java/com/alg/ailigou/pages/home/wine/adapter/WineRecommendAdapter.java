package com.alg.ailigou.pages.home.wine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.wine.holder.WineRecommendHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/8/28
 * 此类或接口用于
 */

public class WineRecommendAdapter extends BaseHeadAndFootAdapter {

    private List<CommodityModel> commodityModels = new ArrayList<>();

    public WineRecommendAdapter(List datas, Context context) {
        super(datas, context);
        this.commodityModels = datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.alg_item_common_goods_info, parent, false);

        return new WineRecommendHolder(inflate, listener, getOnClickListener(), mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WineRecommendHolder) {
            WineRecommendHolder wineRecommendHolder = (WineRecommendHolder) holder;
            wineRecommendHolder.setData(commodityModels.get(position));
        }
    }
}
