package com.alg.ailigou.pages.personal.refundsoraftersale.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.refundsoraftersale.holder.RefundsAfterSaleHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/17
 * 此类或接口用于
 */

public class RefundsAfterSaleAdapter extends BaseHeadAndFootAdapter {


    private LayoutInflater inflater;


    public RefundsAfterSaleAdapter(List datas, Context context) {
        super(datas, context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.alg_item_personal_refunds_aftesale, parent, false);

        return new RefundsAfterSaleHolder(view, listener, getOnClickListener(), mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RefundsAfterSaleHolder) {

            RefundsAfterSaleHolder saleHolder = (RefundsAfterSaleHolder) holder;
            saleHolder.setData(datas.get(position));


        }
    }

}
