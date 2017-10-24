package com.alg.ailigou.pages.personal.withdrawNotes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.ligouchangenotes.holder.LigouChangeNotesHolder;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于 提现记录
 */

public class WthdrawNotesAdapter extends BaseHeadAndFootAdapter {


    public WthdrawNotesAdapter(List datas, Context context, RecyclerView recyclerView) {
        super(datas, context);
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_ligou_change, parent, false);
        return new LigouChangeNotesHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LigouChangeNotesHolder){
            LigouChangeNotesHolder vh = (LigouChangeNotesHolder) holder;
            vh.getTv1().setText("");
            vh.getTv2().setText("");
            vh.getTv3().setText("");
            vh.getTv4().setText("");
        }

    }
}
