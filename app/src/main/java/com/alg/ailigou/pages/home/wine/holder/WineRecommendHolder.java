package com.alg.ailigou.pages.home.wine.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/8/28
 * 此类或接口用于
 */

public class WineRecommendHolder extends MyBaseViewHolder {

    private OnClickListener clickListener;
    private Context context;
    @BindView(R.id.iv_goods_info)
    ImageView ivGoodsInfo;
    @BindView(R.id.tv_goods_desc)
    TextView tvGoodsDesc;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    public WineRecommendHolder(View itemView, OnItemClickListener listener, OnClickListener clickListener, Context context) {
        super(itemView, listener);
        this.clickListener = clickListener;
        this.context = context;
    }

    public void setData(CommodityModel commodityModel) {
        ImageLoadUtils.loadSquareImage(context,commodityModel.imageUrl,ivGoodsInfo,2,10);
        tvGoodsDesc.setText(commodityModel.brand);
        tvPrice.setText("￥:"+commodityModel.price);
    }
}
