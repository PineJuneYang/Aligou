package com.alg.ailigou.pages.cart.newpush.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.cart.newpush.holder.NewPushHolder;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/1
 * 此类或接口用于 新品推送的适配器
 */

public class NewPushAdapter extends BaseHeadAndFootAdapter {


    private LayoutInflater inflater;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public NewPushAdapter(List datas, Context context) {
        super(datas, context);
        inflater = LayoutInflater.from(context);
    }



    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View itemView = inflater.inflate(R.layout.alg_item_cart_new_goods_push, null);
        return new NewPushHolder(itemView, null, mContext ,onClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewPushHolder) {

            NewPushHolder newPushHolder = (NewPushHolder) holder;
            newPushHolder.setData(datas.get(position));
        }


    }


}
