package com.alg.ailigou.pages.mall.weeknew.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.common.widget.AutoImageView;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/7/29
 * 此类或接口用于
 */

public class MallWeekNewHolder extends MyBaseViewHolder {

    @BindView(R.id.iv_week_new_goods)
    AutoImageView ivWeekNewGoods;
    @BindView(R.id.tv_week_new_goods_name)
    TextView tvWeekNewGoodsName;

    private Context mContext;

    public MallWeekNewHolder(View itemView, OnItemClickListener listener, Context context) {
        super(itemView, listener);
        this.mContext = context;


    }


    public void setData(CommodityModel commodityModel) {
//        Glide.with(mContext).load(commodityModel.imageUrl).into(ivWeekNewGoods);
//        ImageLoadUtils.loadSquareImage(mContext,commodityModel.imageUrl,ivWeekNewGoods,2,10);
        ImageLoadUtils.load(mContext,commodityModel.imageUrl,ivWeekNewGoods);
        tvWeekNewGoodsName.setText(commodityModel.title);

    }
}
