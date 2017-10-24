package com.alg.ailigou.pages.cart.newpush.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/1
 * 此类或接口用于 新品推送的viewholder
 */

public class NewPushHolder extends MyBaseViewHolder {

    @BindView(R.id.iv_item_new_push_icon)
    ImageView ivItemNewPushIcon;
    @BindView(R.id.tv_item_new_push_name)
    TextView tvItemNewPushName;
    @BindView(R.id.tv_item_new_push_price)
    TextView tvItemNewPushPrice;
    @BindView(R.id.tv_item_new_push_instant_buy)
    TextView tvItemNewPushInstantBuy;

    private Context mContext;

    private OnClickListener onClickListener;

    public NewPushHolder(View itemView, OnItemClickListener listener, Context context,OnClickListener onClickListener) {
        super(itemView, listener);
        this.mContext  =context;
        this.onClickListener = onClickListener;
    }

    public void setData(Object o) {
        CommodityModel commodityModel = (CommodityModel)o;
        ImageUtils.load(mContext,commodityModel.imageUrl,ivItemNewPushIcon);

        tvItemNewPushName.setText(commodityModel.title);
        tvItemNewPushPrice.setText(String.valueOf(commodityModel.price));

    }

    @OnClick(R.id.tv_item_new_push_instant_buy)
    public void onViewClicked(View view) {
        onClickListener.setOnClickListener(view,getAdapterPosition());
    }
}
