package com.alg.ailigou.pages.cart.entrance.holder;

import android.view.View;
import android.widget.ImageView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于
 */

public class ImageBannerHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;

    public ImageView getIvGoods() {
        return mIvGoods;
    }

    public ImageBannerHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
}
