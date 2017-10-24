package com.alg.ailigou.pages.home.goodslist.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 玖泞
 * on 2017/7/28
 * 此类或接口用于
 */

public class GoodsListViewHolder extends RecyclerView.ViewHolder {

    private boolean isItemView;

    @BindView(R.id.iv_home_goods_list_image)
    ImageView ivHomeGoodsListImage;
    @BindView(R.id.tv_home_goods_list_title)
    TextView tvHomeGoodsListTitle;
    @BindView(R.id.linear_item_goods_list)
    LinearLayout rootView;

    private Context context;
    public GoodsListViewHolder(View itemView, boolean isItem , Context context) {
        super(itemView);

        this.context = context;

        if (isItem){
            //在这里绑定view
            ButterKnife.bind(this,itemView);
        }
    }

    public void setData(CommodityModel commodityModel) {

        Glide.with(context).load(commodityModel.imageUrl).into(ivHomeGoodsListImage);
        tvHomeGoodsListTitle.setText(commodityModel.title);

    }
}
