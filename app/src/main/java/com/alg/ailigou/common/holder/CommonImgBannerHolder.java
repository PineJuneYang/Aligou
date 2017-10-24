package com.alg.ailigou.common.holder;

import android.view.View;
import android.widget.ImageView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/20.
 * 此类或接口用于  功用的banner头布局
 */

public class CommonImgBannerHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_banner)
    ImageView mIvBanner;

    public CommonImgBannerHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    public ImageView getIvBanner() {
        return mIvBanner;
    }
}
