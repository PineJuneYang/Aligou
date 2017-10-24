package com.alg.ailigou.pages.personal.offlineorder.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.OrderOffLineDataModel;
import com.alg.ailigou.library.utils.TimeUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/8/16
 * 此类或接口用于
 */

public class OffLineOrderHolder extends MyBaseViewHolder {


    private OnClickListener clickListener;
    private Context context;

    @BindView(R.id.tv_item_offline_order_number)
    TextView tvItemOfflineOrderNumber;
    @BindView(R.id.tv_item_offline_order_date)
    TextView tvItemOfflineOrderDate;
    @BindView(R.id.tv_item_offline_order_name)
    TextView tvItemOfflineOrderName;
    @BindView(R.id.tv_item_offline_order_price)
    TextView tvItemOfflineOrderPrice;
    @BindView(R.id.tv_item_offline_order_count)
    TextView tvItemOfflineOrderCount;
    @BindView(R.id.tv_item_offline_order_payment_way)
    TextView tvItemOfflineOrderPaymentWay;
    @BindView(R.id.tv_item_offline_order_status)
    TextView tvItemOfflineOrderStatus;
    @BindView(R.id.tv_item_offline_order_account)
    TextView tvItemOfflineOrderAccount;
    @BindView(R.id.tv_item_offline_order_business_name)
    TextView tvItemOfflineOrderBusinessName;
    @BindView(R.id.tv_item_offline_order_telphone)
    TextView tvItemOfflineOrderTelphone;


    public OffLineOrderHolder(View itemView, OnItemClickListener listener, OnClickListener clickListener, Context context) {
        super(itemView, listener);
        this.clickListener = clickListener;
        this.context = context;
    }





    public void setData(OrderOffLineDataModel order) {
        tvItemOfflineOrderNumber.setText(order.order);
        String format = TimeUtils.format(order.time, TimeUtils.PATTERN4);
        tvItemOfflineOrderDate.setText(format);
        if (order.goods!=null&&order.goods.size()>0){
            tvItemOfflineOrderName.setText(order.goods.get(0).title);
            double priceSum = 0;
            for (int i = 0;i<order.goods.size();i++){
                priceSum += Double.parseDouble(order.goods.get(i).price);
            }
            tvItemOfflineOrderPrice.setText(priceSum+"");
            tvItemOfflineOrderCount.setText(order.goods.size()+"");
        }


//        tvItemOfflineOrderStatus.setText(order.);

        tvItemOfflineOrderAccount.setText(order.unionAccount);
        tvItemOfflineOrderBusinessName.setText(order.unionName);
        tvItemOfflineOrderTelphone.setText(order.unionPhone);

    }
}
