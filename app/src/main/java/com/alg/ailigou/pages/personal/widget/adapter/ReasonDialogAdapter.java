package com.alg.ailigou.pages.personal.widget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.widget.holder.ReasonDialogHolder;

import java.util.List;

/**
 * Created by 海航
 * on 2017/8/18.
 * 此类或接口用于
 */

public class ReasonDialogAdapter extends BaseHeadAndFootAdapter {

    private int selectPosition;

    public ReasonDialogAdapter(List datas, Context context) {
        super(datas, context);
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_personal_reason_dialog, parent, false);
        return new ReasonDialogHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ReasonDialogHolder) {
            String deason = (String) datas.get(position);
            ((ReasonDialogHolder) holder).getTvReason().setText(deason);
            if (selectPosition == position) {
                ((ReasonDialogHolder) holder).getIvSelect().setBackgroundResource(R.drawable.alg_cart_checked);
            } else {
                ((ReasonDialogHolder) holder).getIvSelect().setBackgroundResource(R.drawable.alg_cart_choose);
            }
        }
    }


}
