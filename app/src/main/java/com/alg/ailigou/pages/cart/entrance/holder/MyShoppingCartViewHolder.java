package com.alg.ailigou.pages.cart.entrance.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.adapter.OnShoppingCartDataChangedListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/11.
 * 此类或接口用于  购物车单个条目的viewholder
 */

public class MyShoppingCartViewHolder extends MyBaseViewHolder {

    @BindView(R.id.iv_select)
    ImageView mIvSelect;
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_goods_desc)
    TextView mTvGoodsDesc;
    @BindView(R.id.tv_goods_format)
    TextView mTvGoodsFormat;
    @BindView(R.id.tv_goods_price)
    TextView mTvGoodsPrice;
    @BindView(R.id.iv_cart_delete)
    ImageView mIvCartDelete;
    @BindView(R.id.ll_cart_delete)
    LinearLayout mLlCartDelete;
    @BindView(R.id.tv_goods_count)
    TextView mTvGoodsCount;
    @BindView(R.id.iv_cart_add)
    ImageView mIvCartAdd;
    @BindView(R.id.ll_cart_add)
    LinearLayout mLlCartAdd;

    public LinearLayout getLlCartDelete() {
        return mLlCartDelete;
    }

    public LinearLayout getLlCartAdd() {
        return mLlCartAdd;
    }

    private  OnShoppingCartDataChangedListener shoppingListener;
    public MyShoppingCartViewHolder(View itemView, OnItemClickListener listener, OnShoppingCartDataChangedListener shoppingListener) {
        super(itemView, listener);
        this.shoppingListener = shoppingListener;
    }


    public ImageView getIvSelect() {
        return mIvSelect;

    }



    public ImageView getIvGoods() {
        return mIvGoods;
    }

    public TextView getTvGoodsDesc() {
        return mTvGoodsDesc;
    }

    public TextView getTvGoodsFormat() {
        return mTvGoodsFormat;
    }

    public TextView getTvGoodsPrice() {
        return mTvGoodsPrice;
    }

    public ImageView getIvCartDelete() {
        return mIvCartDelete;
    }

    public TextView getTvGoodsCount() {
        return mTvGoodsCount;
    }

    public ImageView getIvCartAdd() {
        return mIvCartAdd;
    }

    @OnClick({R.id.ll_cart_delete, R.id.ll_cart_add, R.id.iv_select})
    public void onViewClicked(View view) {
        switch (view.getId()){

            case R.id.ll_cart_add:
                shoppingListener.setOnAddGoodsCountListener(view,getAdapterPosition());
                break;
            case R.id.ll_cart_delete:
                shoppingListener.setOnDeleteGoodsCountListener(view,getAdapterPosition());
                break;

            case R.id.iv_select:
                shoppingListener.setOnGoodsSelectListener(view,getAdapterPosition());
                break;
        }
    }
}
