package com.alg.ailigou.common.base.adapter;

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
import com.alg.ailigou.pages.home.everydaycheap.holder.EveryDayCheapHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/20.
 * 此类或接口用于  只有一个大图的adapter
 */

public class CommonBigImageAdapter extends BaseHeadAndFootAdapter {

    private List<CommodityModel> datas;

    public CommonBigImageAdapter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_common_big_img, parent, false);
        return new EveryDayCheapHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EveryDayCheapHolder vh = (EveryDayCheapHolder) holder;
        Glide.with(mContext).load(datas.get(position).imageUrlBig).into(vh.getIvGoods());
    }
}
