package com.alg.ailigou.pages.home.searchgoods.adapter;

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
import com.alg.ailigou.pages.home.searchgoods.holder.HistorySearchHolder;
import com.alg.ailigou.pages.home.searchgoods.holder.HotWordsSearchHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 玖泞
 * on 2017/7/31
 * 此类或接口用于
 */

public class HistorySearchHotWordsRecycleAdapter extends BaseHeadAndFootAdapter {



    private Context context;
    private LayoutInflater layoutInflater;

    private List<String> historyWords = new ArrayList<>();

    private OnClickListener onClickListener;

    private List<String> hotWords = new ArrayList<>();

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public HistorySearchHotWordsRecycleAdapter(List datas, Context context, List<String> hotWords) {
        super(datas, context);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.historyWords = datas;
        this.hotWords = hotWords;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View itemView = layoutInflater.inflate(R.layout.alg_item_home_search_history_words, null, false);
        return new HistorySearchHolder(itemView, listener, context, onClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HistorySearchHolder) {
            HistorySearchHolder historySearchHolder = (HistorySearchHolder) holder;
            historySearchHolder.setData(historyWords.get(position));
        }else if (holder instanceof HotWordsSearchHolder){

            HotWordsSearchHolder hotWordsSearchHolder = (HotWordsSearchHolder) holder;
            hotWordsSearchHolder.setData(hotWords);
        }
    }

    @Override
    protected MyBaseViewHolder onHeadViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View headerView = layoutInflater.inflate(R.layout.alg_item_home_search_goods_header, null);
        return new HotWordsSearchHolder(headerView,listener,context);
    }
}
