package com.alg.ailigou.pages.personal.widget.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/18.
 * 此类或接口用于
 */

public class ReasonDialogHolder extends MyBaseViewHolder {
    @BindView(R.id.tv_reason)
    TextView mTvReason;
    @BindView(R.id.iv_select)
    ImageView mIvSelect;
    OnClickListener onClickListener;
    public ReasonDialogHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }
    public ReasonDialogHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener) {
        super(itemView, listener);
    }
    public TextView getTvReason() {
        return mTvReason;
    }

    public ImageView getIvSelect() {
        return mIvSelect;
    }

    @OnClick(R.id.iv_select)
    public void onViewClicked(View view) {
        if (onClickListener!=null){
            onClickListener.setOnClickListener(view,getAdapterPosition());
        }
    }
}
