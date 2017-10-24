package com.alg.ailigou.pages.home.luxurybrand.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/20.
 * 此类或接口用于  奢侈品品牌头部的holder
 */

public class LuxuryBrandHeaderHolder extends MyBaseViewHolder {
    private OnClickListener mClickListener;
    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.iv_goods1)
    ImageView mIvGoods1;
    @BindView(R.id.tv_goods_top_1)
    TextView mTvGoodsTop1;
    @BindView(R.id.tv_goods_bottom_1)
    TextView mTvGoodsBottom1;
    @BindView(R.id.ll_goods1)
    LinearLayout mLlGoods1;
    @BindView(R.id.iv_goods2)
    ImageView mIvGoods2;
    @BindView(R.id.tv_goods_top_2)
    TextView mTvGoodsTop2;
    @BindView(R.id.tv_goods_bottom_2)
    TextView mTvGoodsBottom2;
    @BindView(R.id.ll_goods2)
    LinearLayout mLlGoods2;
    @BindView(R.id.iv_goods3)
    ImageView mIvGoods3;
    @BindView(R.id.tv_goods_top_3)
    TextView mTvGoodsTop3;
    @BindView(R.id.tv_goods_bottom_3)
    TextView mTvGoodsBottom3;
    @BindView(R.id.ll_goods3)
    LinearLayout mLlGoods3;
    @BindView(R.id.iv_goods4)
    ImageView mIvGoods4;
    @BindView(R.id.tv_goods_top_4)
    TextView mTvGoodsTop4;
    @BindView(R.id.tv_goods_bottom_4)
    TextView mTvGoodsBottom4;
    @BindView(R.id.ll_goods4)
    LinearLayout mLlGoods4;
    @BindView(R.id.tv_recommend_title)
    TextView mTvRecommendTitle;
    @BindView(R.id.ll_recommend)
    LinearLayout mLlRecommend;
    @BindView(R.id.iv_brand_goods1)
    ImageView mIvBrandGoods1;
    @BindView(R.id.tv_title1)
    TextView mTvTitle1;
    @BindView(R.id.ll_brand_goods1)
    LinearLayout mLlBrandGoods1;
    @BindView(R.id.iv_brand_goods2)
    ImageView mIvBrandGoods2;
    @BindView(R.id.tv_title2)
    TextView mTvTitle2;
    @BindView(R.id.ll_brand_goods2)
    LinearLayout mLlBrandGoods2;
    @BindView(R.id.iv_brand_goods3)
    ImageView mIvBrandGoods3;
    @BindView(R.id.tv_title3)
    TextView mTvTitle3;
    @BindView(R.id.ll_brand_goods3)
    LinearLayout mLlBrandGoods3;
    @BindView(R.id.iv_brand_goods4)
    ImageView mIvBrandGoods4;
    @BindView(R.id.tv_title4)
    TextView mTvTitle4;
    @BindView(R.id.ll_brand_goods4)
    LinearLayout mLlBrandGoods4;
    @BindView(R.id.iv_brand_goods5)
    ImageView mIvBrandGoods5;
    @BindView(R.id.tv_title5)
    TextView mTvTitle5;
    @BindView(R.id.ll_brand_goods5)
    LinearLayout mLlBrandGoods5;
    @BindView(R.id.iv_brand_goods6)
    ImageView mIvBrandGoods6;
    @BindView(R.id.tv_title6)
    TextView mTvTitle6;
    @BindView(R.id.ll_brand_goods6)
    LinearLayout mLlBrandGoods6;
    @BindView(R.id.iv_brand_goods7)
    ImageView mIvBrandGoods7;
    @BindView(R.id.tv_title7)
    TextView mTvTitle7;
    @BindView(R.id.ll_brand_goods7)
    LinearLayout mLlBrandGoods7;
    @BindView(R.id.iv_brand_goods8)
    ImageView mIvBrandGoods8;
    @BindView(R.id.tv_title8)
    TextView mTvTitle8;
    @BindView(R.id.ll_brand_goods8)
    LinearLayout mLlBrandGoods8;
    public LuxuryBrandHeaderHolder(View itemView, OnItemClickListener listener, OnClickListener clickListener) {
        super(itemView, listener);
        this.mClickListener = clickListener;
    }
    @OnClick({R.id.ll_goods1, R.id.ll_goods2, R.id.ll_goods3, R.id.ll_goods4, R.id.ll_brand_goods1, R.id.ll_brand_goods2, R.id.ll_brand_goods3, R.id.ll_brand_goods4, R.id.ll_brand_goods5, R.id.ll_brand_goods6, R.id.ll_brand_goods7, R.id.ll_brand_goods8})
    public void onViewClicked(View view) {
        if(mClickListener!=null){
            mClickListener.setOnClickListener(view,getAdapterPosition());
        }

    }
}
