package com.alg.ailigou.pages.home.hotsalelist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.hotsalelist.holder.CommonViewHolder;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/29
 * 此类或接口用于
 */

public class HotSaleRecycleAdapter extends BaseHeadAndFootAdapter {


    private List<CommodityModel> datas;
    private LayoutInflater inflater;

    private OnClickListener mOnClickListener;





    public void setOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public HotSaleRecycleAdapter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.alg_item_hot_sale_list, parent, false);
        return new CommonViewHolder(view, getListener(), mOnClickListener,mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommonViewHolder){
            CommonViewHolder commonViewHolder = (CommonViewHolder)holder;
            commonViewHolder.setData(datas.get(position));
        }

    }

    public void setLoading(boolean isLoading){
        this.isLoading = isLoading;
    }
}








