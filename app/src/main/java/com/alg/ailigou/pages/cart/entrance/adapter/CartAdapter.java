package com.alg.ailigou.pages.cart.entrance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.adapter.OnShoppingCartDataChangedListener;
import com.alg.ailigou.pages.cart.entrance.holder.MyShoppingCartViewHolder;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/9/19
 * 此类或接口用于
 */

public class CartAdapter extends BaseHeadAndFootAdapter {



    private OnShoppingCartDataChangedListener shoppingCartDataChangedListener ;


    public void setShoppingCartDataChangedListener(OnShoppingCartDataChangedListener shoppingCartDataChangedListener) {
        this.shoppingCartDataChangedListener = shoppingCartDataChangedListener;
    }

    public CartAdapter(List datas, Context context) {
        super(datas, context);
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View cartView = LayoutInflater.from(mContext).inflate(R.layout.alg_item_shopping_cart, parent, false);
        return new MyShoppingCartViewHolder(cartView, listener, shoppingCartDataChangedListener );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyShoppingCartViewHolder) {
            MyShoppingCartViewHolder viewHolder = (MyShoppingCartViewHolder) holder;
            CommodityModel bean = (CommodityModel) datas.get(position);
            viewHolder.getTvGoodsCount().setText("" + bean.cartCount);
            viewHolder.getTvGoodsPrice().setText("" + bean.price);
            viewHolder.getTvGoodsDesc().setText(bean.title);
            viewHolder.getTvGoodsFormat().setText(bean.describe);
            ImageLoadUtils.load(mContext, ((CommodityModel) datas.get(position)).imageUrl, viewHolder.getIvGoods());
//                Glide.with(mContext).load(((CommodityModel) datas.get(position)).imageUrl).into(viewHolder.getIvGoods());
            if (bean.isSelect) {
                viewHolder.getIvSelect().setBackgroundResource(R.drawable.alg_cart_checked);
            } else {
                viewHolder.getIvSelect().setBackgroundResource(R.drawable.alg_cart_choose);
            }
        }
    }


}
