package com.alg.ailigou.pages.cart.entrance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.pages.cart.adapter.BaseVLayoutAdapter;
import com.alg.ailigou.pages.cart.adapter.OnShoppingCartDataChangedListener;
import com.alg.ailigou.pages.cart.entrance.holder.GoodsRecommendViewHolder;
import com.alg.ailigou.pages.cart.entrance.holder.ImageBannerHolder;
import com.alg.ailigou.pages.cart.entrance.holder.MyShoppingCartViewHolder;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/11.
 * 此类或接口用于  购物车上面banner+购物车的  adapter
 */

public class ShoppingCartAdapter<T> extends BaseVLayoutAdapter {

    //如果是一个banner  那么datas  传进来的是banner  图片地址

    public ShoppingCartAdapter(List datas, Context context, LayoutHelper helper) {
        super(datas, context, helper);
    }

    public ShoppingCartAdapter(List datas, Context context) {
        super(datas, context);
    }

    public
    ShoppingCartAdapter(List datas, Context context, LayoutHelper helper, int type) {
        super(datas, context, helper, type);
    }

    public OnShoppingCartDataChangedListener getShoppingListener() {
        return mShoppingListener;
    }

    public void setShoppingListener(OnShoppingCartDataChangedListener shoppingListener) {
        mShoppingListener = shoppingListener;
    }

    private OnShoppingCartDataChangedListener mShoppingListener;

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        MyBaseViewHolder vh = null;
        switch (viewType) {
            case BASE_ITEM_TYPE_HEADER_BANNER:
                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_cart_image_banner, parent, false);
                vh = new ImageBannerHolder(view, null);
                break;
            case BASE_ITEM_TYPE_HEADER_TITLE:
                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_shopping_cart_recommend_title, parent, false);
                TextView tv = (TextView) view.findViewById(R.id.tv_recommend_title);
                vh = new MyBaseViewHolder(view, null);
                break;
            case BASE_ITEM_TYPE_CRAT:
                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_shopping_cart, parent, false);
                vh = new MyShoppingCartViewHolder(view, null,null);
                break;
            case BASE_ITEM_TYPE_RECOMMEND:
                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_common_goods_info, parent, false);
                vh = new GoodsRecommendViewHolder(view, null);
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            //购物车的item
            case BASE_ITEM_TYPE_CRAT:
                MyShoppingCartViewHolder viewHolder = (MyShoppingCartViewHolder) holder;
                CommodityModel bean = (CommodityModel) datas.get(position);
                viewHolder.getTvGoodsCount().setText("" + bean.cartCount);
                viewHolder.getTvGoodsPrice().setText("" + bean.price);
                viewHolder.getTvGoodsDesc().setText(bean.title);
                viewHolder.getTvGoodsFormat().setText(bean.describe);
                initShoppingCartListener(viewHolder, position);
                ImageLoadUtils.load(mContext,((CommodityModel) datas.get(position)).imageUrl,viewHolder.getIvGoods());
//                Glide.with(mContext).load(((CommodityModel) datas.get(position)).imageUrl).into(viewHolder.getIvGoods());
                if (bean.isSelect) {
                    viewHolder.getIvSelect().setBackgroundResource(R.drawable.alg_cart_checked);
                } else {
                    viewHolder.getIvSelect().setBackgroundResource(R.drawable.alg_cart_choose);
                }
                break;
            //推荐商品的item
            case BASE_ITEM_TYPE_RECOMMEND:
                GoodsRecommendViewHolder vh = (GoodsRecommendViewHolder) holder;
                ImageLoadUtils.load(mContext,((CommodityModel) datas.get(position)).imageUrl,vh.getIvGoodsInfo());
                vh.getTvGoodsDesc().setText(((CommodityModel) datas.get(position)).title);
                vh.getTvPrice().setText("价格为" + ((CommodityModel) datas.get(position)).price);
                vh.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getListener() != null) {
                            getListener().setOnItemClickListener(v, position);
                        }
                    }
                });
                break;
            //最上面的banner
            case BASE_ITEM_TYPE_HEADER_BANNER:
                ImageView imageView = ((ImageBannerHolder) holder).getIvGoods();
                if (datas!=null){
                    BannerModel bannerModel = (BannerModel) datas.get(0);
                    ImageLoadUtils.load(mContext,bannerModel.image,imageView);
                }
                ((ImageBannerHolder) holder).getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getListener() != null) {
                            getListener().setOnItemClickListener(v, position);
                        }
                    }
                });
                break;
        }

    }

    /**
     * g购物车条目的监听处理
     *
     * @param viewHolder
     * @param position
     */
    private void initShoppingCartListener(MyShoppingCartViewHolder viewHolder, final int position) {
        //  这个是点击 +  号的按钮  监听
        viewHolder.getLlCartAdd().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShoppingListener != null) {
                    mShoppingListener.setOnAddGoodsCountListener(v, position);
                }
            }
        });
        //  这个是点击 -  号的按钮  监听
        viewHolder.getLlCartDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShoppingListener != null) {
                    mShoppingListener.setOnDeleteGoodsCountListener(v, position);
                }
            }
        });
        //点击选中
        viewHolder.getIvSelect().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShoppingListener != null) {
                    mShoppingListener.setOnGoodsSelectListener(v, position);
                }
            }
        });
        viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getListener() != null) {
                    getListener().setOnItemClickListener(v, position);
                }
            }
        });
    }


}
