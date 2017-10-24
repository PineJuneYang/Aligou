package com.alg.ailigou.pages.home.hotrecommend.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/21.
 * 此类或接口用于
 */

public class HotRecommendHolder extends MyBaseViewHolder {

    @BindView(R.id.iv_goods1)
    ImageView ivGoods1;
    @BindView(R.id.tv_goods_title1)
    TextView tvGoodsTitle1;
    @BindView(R.id.tv_goods_price1)
    TextView tvGoodsPrice1;
    @BindView(R.id.ll_left_item)
    LinearLayout llLeftItem;
    @BindView(R.id.iv_goods2)
    ImageView ivGoods2;
    @BindView(R.id.tv_goods_title2)
    TextView tvGoodsTitle2;
    @BindView(R.id.tv_goods_price2)
    TextView tvGoodsPrice2;
    @BindView(R.id.ll_right_item)
    LinearLayout llRightItem;

    @BindView(R.id.ll_home_recommend_creazy_bg)
    LinearLayout llHomeRecommendCreazyBg;


    public HotRecommendHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
    }

    public LinearLayout getLlHomeRecommendCreazyBg() {
        return llHomeRecommendCreazyBg;
    }

    public ImageView getIvGoods1() {
        return ivGoods1;
    }

    public TextView getTvGoodsTitle1() {
        return tvGoodsTitle1;
    }

    public TextView getTvGoodsPrice1() {
        return tvGoodsPrice1;
    }

    public LinearLayout getLlLeftItem() {
        return llLeftItem;
    }

    public ImageView getIvGoods2() {
        return ivGoods2;
    }

    public TextView getTvGoodsTitle2() {
        return tvGoodsTitle2;
    }

    public TextView getTvGoodsPrice2() {
        return tvGoodsPrice2;
    }

    public LinearLayout getLlRightItem() {
        return llRightItem;
    }
}
