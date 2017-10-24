package com.alg.ailigou.pages.home.searchgoods.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/7/31
 * 此类或接口用于
 */

public class HistorySearchHolder extends MyBaseViewHolder {

    @BindView(R.id.tv_item_history_words_name)
    TextView tvItemHistoryWordsName;
    private Context mContext;
    private OnClickListener onClickListener;
    private OnItemClickListener onItemClickListener;

    public HistorySearchHolder(View itemView, OnItemClickListener listener, Context context, OnClickListener onClickListener) {
        super(itemView, listener);
        this.mContext = context;
        this.onItemClickListener = listener;
        this.onClickListener = onClickListener;

    }

    public void setData(String s) {
        tvItemHistoryWordsName.setText(s);
    }
}
