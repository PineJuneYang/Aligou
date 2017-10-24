package com.alg.ailigou.pages.cart.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;

import butterknife.ButterKnife;

/**
 * Created by 海航
 * on 2017/7/11.
 * 此类或接口用于  ViewHolder的基类
 */

public class MyBaseViewHolder extends RecyclerView.ViewHolder {
    public View itemView;

    public MyBaseViewHolder(View itemView, final OnItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        if (listener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.setOnItemClickListener(v, getAdapterPosition());
                }
            });
        }
        this.itemView = itemView;
    }

    public View getItemView() {
        return itemView;
    }
}

