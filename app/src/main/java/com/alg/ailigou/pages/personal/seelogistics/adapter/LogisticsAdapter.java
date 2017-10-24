package com.alg.ailigou.pages.personal.seelogistics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.LogisticsModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.seelogistics.holder.LogisticsHolder;

import java.util.List;

/**
 * Created by 海航
 * on 2017/8/9.
 * 此类或接口用于  物流的adapter
 */

public class LogisticsAdapter extends BaseHeadAndFootAdapter {

    private List<LogisticsModel.TracesBean> datas;

    public LogisticsAdapter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_personal_logistics, parent, false);
        return new LogisticsHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LogisticsHolder){
            LogisticsHolder vh = (LogisticsHolder) holder;
            if (position == 0) {
                vh.getTvLineTop().setVisibility(View.INVISIBLE);
                vh.getIvCircle().setBackgroundResource(R.drawable.alg_personal_logistcs_select);
            } else {
                vh.getIvCircle().setBackgroundResource(R.drawable.alg_personal_logistcs_no_select);
                vh.getTvLineTop().setVisibility(View.VISIBLE);
            }
            vh.getTvLogisticsDesc().setText(datas.get(position).AcceptStation);
            vh.getTvLogisticsTime().setText(datas.get(position).AcceptTime);
        }

    }
}
