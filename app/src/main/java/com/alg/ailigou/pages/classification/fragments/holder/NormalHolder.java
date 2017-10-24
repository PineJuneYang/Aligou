package com.alg.ailigou.pages.classification.fragments.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/8/2
 * 此类或接口用于
 */

public class NormalHolder extends MyBaseViewHolder {
    @BindView(R.id.iv_hot_image)
    ImageView ivHotImage;
    @BindView(R.id.tv_hot_title)
    TextView tvHotTitle;


    public NormalHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }


    public ImageView getIvHotImage() {
        return ivHotImage;
    }

    public TextView getTvHotTitle() {
        return tvHotTitle;
    }
}
