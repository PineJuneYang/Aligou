package com.alg.ailigou.pages.personal.seelogistics.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/8/9.
 * 此类或接口用于
 */

public class LogisticsHolder extends MyBaseViewHolder {
    @BindView(R.id.tv_line_top)
    TextView mTvLineTop;
    @BindView(R.id.iv_circle)
    ImageView mIvCircle;
    @BindView(R.id.tv_logistics_desc)
    TextView mTvLogisticsDesc;
    @BindView(R.id.tv_logistics_time)
    TextView mTvLogisticsTime;

    public TextView getTvLineTop() {
        return mTvLineTop;
    }

    public ImageView getIvCircle() {
        return mIvCircle;
    }

    public TextView getTvLogisticsDesc() {
        return mTvLogisticsDesc;
    }

    public TextView getTvLogisticsTime() {
        return mTvLogisticsTime;
    }

    public LogisticsHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
}
