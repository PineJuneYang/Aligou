package com.alg.ailigou.pages.union.uniondetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.union.uniondetail.holder.UniconShowHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/27.
 * 此类或接口用于
 */

public class UnionShowAdapter extends BaseHeadAndFootAdapter {
    private  List<String> imgs;
    public UnionShowAdapter(List datas, Context context) {
        super(datas, context);
        this.imgs=datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.alg_item_union_show,parent,false);
        return new UniconShowHolder(view,getListener());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UniconShowHolder){
        UniconShowHolder vh= (UniconShowHolder) holder;
        Glide.with(mContext).load(imgs.get(position)).into(vh.getImageView());
    }
    }
}
