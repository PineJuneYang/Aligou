package com.alg.ailigou.pages.personal.receiveaddress.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.ShippingAddressModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.personal.receiveaddress.holder.AddressHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/14
 * 此类或接口用于
 */

public class AddressAdapter extends BaseHeadAndFootAdapter {


    private LayoutInflater inflater;

    public AddressAdapter(List datas, Context context) {
        super(datas, context);
        inflater = LayoutInflater.from(context);

    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.alg_item_personal_receive_address, parent, false);
        return new AddressHolder(view, getListener(), getOnClickListener(), mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AddressHolder) {
            AddressHolder addressHolder = (AddressHolder) holder;
            addressHolder.setData((ShippingAddressModel) datas.get(position));

        }

    }


}
