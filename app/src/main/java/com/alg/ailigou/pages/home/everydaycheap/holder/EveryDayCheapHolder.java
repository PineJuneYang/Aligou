package com.alg.ailigou.pages.home.everydaycheap.holder;

import android.view.View;
import android.widget.ImageView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/20.
 * 此类或接口用于
 */

public class EveryDayCheapHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    public EveryDayCheapHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    public ImageView getIvGoods() {
        return mIvGoods;
    }
}
