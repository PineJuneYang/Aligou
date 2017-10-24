package com.alg.ailigou.pages.home.search.holder;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.common.widget.AutoImageView;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/7/26
 * 此类或接口用于
 */

public class SearchDetailHolder extends MyBaseViewHolder {

    private Context context;
    private OnClickListener onClickListener;



    @BindView(R.id.tv_item_search_detail_goods_name)
    TextView tvItemSearchDetailGoodsName;
    @BindView(R.id.tv_item_search_detail_price)
    TextView tvItemSearchDetailPrice;
    @BindView(R.id.linear_item_search_detail)
    LinearLayout linearItemSearchDetail;
    @BindView(R.id.iv_item_search_detail_goods)
    AutoImageView ivItemSearchDetailGoods;

//    public SearchDetailHolder(View itemView, OnItemClickListener listener, OnClickListener onClickListener, Context mContext) {
//        super(itemView);
//        ButterKnife.bind(this,itemView);
//        this.onClickListener = onClickListener;
//        this.context = mContext;
//    }

    public SearchDetailHolder(View view, OnItemClickListener listener, OnClickListener onClickListener, Context mContext) {
        super(view, listener);
        this.onClickListener = onClickListener;
        this.context = mContext;
    }

    public void setData(CommodityModel data){


        //同样可以加载出正方形的图片
        ImageLoadUtils.load(context,data.imageUrl,ivItemSearchDetailGoods);
//        ImageLoadUtils.loadSquareImage(context,data.imageUrl,ivItemSearchDetailGoods,2,);

//        ImageLoadUtils.loadSquareImage(context,data.imageUrl,ivItemSearchDetailGoods,2,);

        tvItemSearchDetailGoodsName.setText(data.title);
        tvItemSearchDetailPrice.setText("￥"+data.price);
    }

    @OnClick(R.id.linear_item_search_detail)
    public void onViewClicked(View view) {
        onClickListener.setOnClickListener(view,getAdapterPosition());
    }




}
