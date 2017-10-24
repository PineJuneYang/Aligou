package com.alg.ailigou.pages.home.ligouchangenotes.holder;

import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于  利购券兑换记录
 */

public class LigouChangeNotesHolder extends MyBaseViewHolder {
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.tv_4)
    TextView mTv4;

    public TextView getTv4Desc() {
        return mTv4Desc;
    }

    @BindView(R.id.tv_4_desc)
    TextView mTv4Desc;

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

    public LigouChangeNotesHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
}
