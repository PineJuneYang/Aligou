package com.alg.ailigou.pages.union.uniondetail.holder;

import android.view.View;
import android.widget.ImageView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/27.
 * 此类或接口用于
 */

public class UniconShowHolder extends MyBaseViewHolder {


    @BindView(R.id.iv_unicon)
    ImageView mImageView;

    public UniconShowHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    public ImageView getImageView() {
        return mImageView;
    }
}
