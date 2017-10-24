package com.alg.ailigou.pages.personal.myorder.holder;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/9
 * 此类或接口用于
 */

public class PreCommentViewHolder extends MyBaseViewHolder {

    @BindView(R.id.iv_my_order_commondity_icon)
    ImageView ivMyOrderCommondityIcon;
    @BindView(R.id.tv_my_order_commondity_name)
    TextView tvMyOrderCommondityName;
    @BindView(R.id.tv_my_order_commondity_now_price)
    TextView tvMyOrderCommondityNowPrice;
    @BindView(R.id.tv_my_order_commondity_pre_price)
    TextView tvMyOrderCommondityPrePrice;
    @BindView(R.id.tv_my_order_specification)
    TextView tvMyOrderSpecification;
    @BindView(R.id.rl_my_order_data)
    RelativeLayout rlMyOrderData;
    @BindView(R.id.tv_item_my_order_fare)
    TextView tvItemMyOrderFare;
    @BindView(R.id.tv_item_my_order_total_price)
    TextView tvItemMyOrderTotalPrice;
    @BindView(R.id.tv_item_my_order_total)
    TextView tvItemMyOrderTotal;
    @BindView(R.id.tv_item_my_order_commondity_count)
    TextView tvItemMyOrderCommondityCount;
    @BindView(R.id.tv_item_my_order_funds)
    TextView tvItemMyOrderFunds;
    @BindView(R.id.tv_item_my_order_cancle)
    TextView tvItemMyOrderCancle;
    @BindView(R.id.tv_item_my_order_view_logistics)
    TextView tvItemMyOrderViewLogistics;

    private OnClickListener mClickListener;
    private Context mContext;
    private int position;

    public PreCommentViewHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener, int position, Context context) {
        super(itemView, listener);
        this.mClickListener =onClickListener;
        this.mContext = context;
        this.position = position;
    }

    public void setData(OrderDetailsDataModel commodityModel) {

//        Glide.with(mContext).load(commodityModel.goods.get(0).imageUrl).into(ivMyOrderCommondityIcon);
        ImageLoadUtils.load(mContext,commodityModel.goods.get(0).imageUrl,ivMyOrderCommondityIcon);
        tvMyOrderCommondityName.setText(commodityModel.goods.get(0).title);
        tvMyOrderCommondityNowPrice.setText(String.valueOf(commodityModel.goods.get(0).price));
        tvMyOrderCommondityPrePrice.setText(String.valueOf(commodityModel.goods.get(0).oldPrice));
        //设置删除线
        tvMyOrderCommondityPrePrice.setPaintFlags(tvMyOrderCommondityPrePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//        tvMyOrderSpecification.setText(commodityModel.cartCount);
//        tvItemMyOrderFare.setText(commodityModel.courier);
//
//        tvItemMyOrderTotalPrice.setText(String.valueOf(commodityModel.price+commodityModel.courier));
        tvItemMyOrderTotalPrice.setText(commodityModel.allPrice+"");

        tvItemMyOrderCommondityCount.setText("共"+commodityModel.goods.size()+"件商品");

        switch (position){

            case 0:


                break;

            case 1:

                tvItemMyOrderFunds.setText("付款");
                tvItemMyOrderCancle.setVisibility(View.VISIBLE);
                tvItemMyOrderCancle.setText("取消订单");
                tvItemMyOrderViewLogistics.setVisibility(View.GONE);
                break;

            case 2:

                tvItemMyOrderFunds.setText("退款");
                tvItemMyOrderCancle.setVisibility(View.GONE);
                tvItemMyOrderViewLogistics.setVisibility(View.GONE);
                break;

            case 3:

                tvItemMyOrderFunds.setText("退款");
                tvItemMyOrderCancle.setText("确认收货");
                tvItemMyOrderCancle.setVisibility(View.VISIBLE);
                tvItemMyOrderViewLogistics.setText("查看物流");
                tvItemMyOrderViewLogistics.setVisibility(View.VISIBLE);

                break;

            case 4:


                tvItemMyOrderFunds.setText("评价");
                tvItemMyOrderCancle.setText("查看物流");
                tvItemMyOrderCancle.setVisibility(View.VISIBLE);
                tvItemMyOrderViewLogistics.setVisibility(View.GONE);

                break;



        }



    }
    @OnClick({R.id.tv_item_my_order_funds, R.id.tv_item_my_order_cancle, R.id.tv_item_my_order_view_logistics})
    public void onViewClicked(View view) {
        mClickListener.setOnClickListener(view,getAdapterPosition());
    }


}
