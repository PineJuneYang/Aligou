package com.alg.ailigou.pages.personal.moneychange.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.MoneyChangeModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.moneychange.holder.MoneyChangeHolder;

import java.util.List;

/**
 * Created by 海航
 * on 2017/8/29.
 * 此类或接口用于  资金变动记录
 */

public class MoneyChangeAdapter extends BaseHeadAndFootAdapter {


    public MoneyChangeAdapter(List datas, Context context) {
        super(datas, context);
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_personal_money_change, parent, false);
        return new MoneyChangeHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MoneyChangeHolder) {
            MoneyChangeHolder vh = (MoneyChangeHolder) holder;
            MoneyChangeModel model = (MoneyChangeModel) datas.get(position);
            vh.getTv1().setText(model.changeMoney + "");
            vh.getTv2().setText(model.canUseMoney + "");
            vh.getTv3().setText(model.freezeMoney + "");
            vh.getTv4().setText(model.remark);

        }
    }
}
