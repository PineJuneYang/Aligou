package com.alg.ailigou.pages.personal.receiveaddress.holder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.ShippingAddressModel;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/14
 * 此类或接口用于
 */

public class AddressHolder extends MyBaseViewHolder {


    private Context context;
    private OnClickListener listener;


    @BindView(R.id.tv_item_receive_address_name)
    TextView tvItemReceiveAddressName;
    @BindView(R.id.tv_item_receive_address_phone)
    TextView tvItemReceiveAddressPhone;
    @BindView(R.id.tv_item_receive_address_address)
    TextView tvItemReceiveAddressAddress;
    @BindView(R.id.tv_item_receive_address_default)
    TextView tvItemReceiveAddressDefault;
    @BindView(R.id.tv_item_receive_address_delete)
    TextView tvItemReceiveAddressDelete;
    @BindView(R.id.tv_item_receive_address_edit)
    TextView tvItemReceiveAddressEdit;


    public AddressHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener, Context mContext) {
        super(itemView, listener);
        this.context = mContext;
        this.listener = onClickListener;
    }

    public void setData(ShippingAddressModel data) {
        tvItemReceiveAddressName.setText(data.name);
        tvItemReceiveAddressPhone.setText(data.telNumber);
        tvItemReceiveAddressAddress.setText(data.address);
        if (getAdapterPosition()==0){
            Drawable drawable = context.getResources().getDrawable(R.drawable.alg_cart_all_choose);
            tvItemReceiveAddressDefault.setCompoundDrawables(drawable,null,null,null);
        }

    }

    @OnClick({R.id.tv_item_receive_address_name, R.id.tv_item_receive_address_phone, R.id.tv_item_receive_address_address, R.id.tv_item_receive_address_default, R.id.tv_item_receive_address_delete, R.id.tv_item_receive_address_edit})
    public void onViewClicked(View view) {
        listener.setOnClickListener(view,getAdapterPosition());
    }

}
