package com.alg.ailigou.pages.personal.refundsoraftersale.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/18
 * 此类或接口用于
 */

public class RefundsAfterSaleRecycleHolder extends MyBaseViewHolder {



    @BindView(R.id.recyler_view)
    RecyclerView recylerView;

    @BindView(R.id.tv_refunds_aftersale_refunds_status)
    TextView tvRefundsAftersaleRefundsStatus;
    @BindView(R.id.tv_refunds_aftersale_detail)
    TextView tvRefundsAftersaleDetail;
    @BindView(R.id.tv_refunds_aftersale_refunds_money)
    TextView tvRefundsAftersaleRefundsMoney;

    private OnClickListener onClickListener;
    private Context context;



    public RecyclerView getRecylerView() {
        return recylerView;
    }

    public void setRecylerView(RecyclerView recylerView) {
        this.recylerView = recylerView;
    }

    public RefundsAfterSaleRecycleHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener, Context context) {
        super(itemView, listener);
        this.onClickListener = onClickListener;
        this.context = context;
    }
    @OnClick(R.id.tv_refunds_aftersale_detail)
    public void onViewClicked(View view) {

        onClickListener.setOnClickListener(view,getAdapterPosition());
    }

    public void setData(ReturnGoodsData returnGoodsData) {

//        tvRefundsAftersaleRefundsStatus.setText();
//        tvRefundsAftersaleDetail.setText();
        tvRefundsAftersaleRefundsMoney.setText(returnGoodsData.returnMoney+"");
    }
}
