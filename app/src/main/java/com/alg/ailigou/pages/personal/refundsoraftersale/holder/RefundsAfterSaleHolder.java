package com.alg.ailigou.pages.personal.refundsoraftersale.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/18
 * 此类或接口用于
 */

public class RefundsAfterSaleHolder extends MyBaseViewHolder {




    @BindView(R.id.iv_refunds_aftersale_icon)
    ImageView ivRefundsAftersaleIcon;
    @BindView(R.id.tv_refunds_aftersale_brand)
    TextView tvRefundsAftersaleBrand;
    @BindView(R.id.tv_refunds_aftersale_specification)
    TextView tvRefundsAftersaleSpecification;
    @BindView(R.id.tv_refunds_aftersale_price)
    TextView tvRefundsAftersalePrice;
    @BindView(R.id.tv_refunds_aftersale_count)
    TextView tvRefundsAftersaleCount;





    private OnClickListener clickListener;
    private Context context;

    public RefundsAfterSaleHolder(View itemView, OnItemClickListener listener, OnClickListener clickListener, Context context) {
        super(itemView, listener);
        this.clickListener = clickListener;
        this.context = context;
    }

    public void setData(Object o) {
        CommodityModel commodityModel = (CommodityModel)o;
        Glide.with(context).load(commodityModel.imageUrl).into(ivRefundsAftersaleIcon);
        tvRefundsAftersaleBrand.setText(commodityModel.brand);
//        tvRefundsAftersaleSpecification.setText(commodityModel.);
        tvRefundsAftersalePrice.setText("￥:"+commodityModel.price);
        tvRefundsAftersaleCount.setText(""+commodityModel.buyCount);
    }


}
