package com.alg.ailigou.pages.mall.limit.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/22.
 * 此类或接口用于
 */

public class LimitHeadHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_mall_banner)
    ImageView mIvMallBanner;


    public ImageView getIvMallBanner() {
        return mIvMallBanner;
    }


    public LimitHeadHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

}
