package com.alg.ailigou.pages.home.hotsalelist.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/7/21
 * 此类或接口用于
 */

public  class CommonViewHolder extends MyBaseViewHolder {

    @BindView(R.id.iv_item_hot_sale)
    ImageView ivItemHotSale;
    @BindView(R.id.iv_item_hot_sale_icon)
    ImageView ivItemHotSaleIcon;
    @BindView(R.id.tv_item_hot_sale_name)
    TextView tvItemHotSaleName;
    @BindView(R.id.tv_item_hot_sale_desc)
    TextView tvItemHotSaleDesc;
    @BindView(R.id.tv_item_hot_sale_now_price)
    TextView tvItemHotSaleNowPrice;
    @BindView(R.id.tv_item_hot_sale_pre_price)
    TextView tvItemHotSalePrePrice;
    @BindView(R.id.tv_item_hot_sale_save)
    TextView tvItemHotSaleSave;
    @BindView(R.id.linearLayout_item_hot_sale_list)
    LinearLayout linearLayoutItemHotSaleList;

    private OnClickListener onClickListener;
    private Context mContext;

    public CommonViewHolder(View view, OnItemClickListener onItemClickListener, OnClickListener mOnClickListener, Context context) {
        super(view, onItemClickListener);
        this.onClickListener = mOnClickListener;
        this.mContext = context;
    }




    @OnClick({R.id.iv_item_hot_sale_icon, R.id.tv_item_hot_sale_save, R.id.linearLayout_item_hot_sale_list})
    public void onViewClicked(View view) {
        onClickListener.setOnClickListener(view,getAdapterPosition());
    }


    public void setData(CommodityModel commodityModel) {

        tvItemHotSaleName.setText(commodityModel.title);
        Glide.with(mContext).load(commodityModel.imageUrl).into(ivItemHotSaleIcon);
        tvItemHotSaleDesc.setVisibility(View.GONE);
        tvItemHotSaleNowPrice.setText("￥"+commodityModel.price);
        tvItemHotSalePrePrice.setText("￥"+commodityModel.oldPrice);
        double abs = Math.abs(Double.parseDouble(commodityModel.oldPrice) - Double.parseDouble(commodityModel.price));
        tvItemHotSaleSave.setText("立省"+abs+"元");
    }
}
