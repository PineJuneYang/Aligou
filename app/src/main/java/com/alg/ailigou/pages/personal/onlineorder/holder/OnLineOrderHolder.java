package com.alg.ailigou.pages.personal.onlineorder.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/16
 * 此类或接口用于
 */

public class OnLineOrderHolder extends MyBaseViewHolder {
    @BindView(R.id.tv_item_online_order_number)
    TextView tvItemOnlineOrderNumber;
    @BindView(R.id.tv_item_online_order_date)
    TextView tvItemOnlineOrderDate;
    @BindView(R.id.iv_item_online_order_icon)
    ImageView ivItemOnlineOrderIcon;
    @BindView(R.id.tv_item_online_order_price)
    TextView tvItemOnlineOrderPrice;
    @BindView(R.id.tv_item_online_order_status)
    TextView tvItemOnlineOrderStatus;
    @BindView(R.id.tv_online_order_view)
    TextView tvOnlineOrderView;

    private OnClickListener clickListener;
    private Context context;


    public OnLineOrderHolder(View itemView, OnItemClickListener listener, OnClickListener clickListener, Context context) {
        super(itemView, listener);
        this.clickListener = clickListener;
        this.context = context;
    }




    @OnClick(R.id.tv_online_order_view)
    public void onViewClicked(View view) {
        if (clickListener!=null){
            clickListener.setOnClickListener(view,getAdapterPosition());
        }
    }


    public void setData(OrderDetailsDataModel orderDetailsDataModel) {
//        OrderDetailsDataModel orderDetailsDataModel =   (OrderDetailsDataModel)o;
        tvItemOnlineOrderNumber.setText(orderDetailsDataModel.orderNumber);
        tvItemOnlineOrderDate.setText(orderDetailsDataModel.orderTime+"");
       if (orderDetailsDataModel.goods!=null&&orderDetailsDataModel.goods.size()>0){
           Glide.with(context).load(orderDetailsDataModel.goods.get(0).imageUrl).into(ivItemOnlineOrderIcon);
           tvItemOnlineOrderPrice.setText(orderDetailsDataModel.goods.get(0).price+"");
       }
        tvItemOnlineOrderStatus.setText(getTypes().get(orderDetailsDataModel.orderState));
    }
    ArrayList list = new ArrayList();

    public List<String> getTypes() {
        if (list.size()==0){
            list.add("");//0应该不显示内容吧
            list.add("待付款");
            list.add("待发货");
            list.add("代收货");
            list.add("待评价");
            list.add("已完成");
            list.add("退货");
        }
        return list;
    }
}
