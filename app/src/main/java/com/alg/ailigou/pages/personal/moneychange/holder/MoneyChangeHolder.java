package com.alg.ailigou.pages.personal.moneychange.holder;

import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/8/29.
 * 此类或接口用于
 */

public class MoneyChangeHolder extends MyBaseViewHolder{
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.tv4)
    TextView mTv4;

    public TextView getTv1() {
        return mTv1;
    }

    public TextView getTv2() {
        return mTv2;
    }

    public TextView getTv3() {
        return mTv3;
    }

    public TextView getTv4() {
        return mTv4;
    }

    public MoneyChangeHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
}
