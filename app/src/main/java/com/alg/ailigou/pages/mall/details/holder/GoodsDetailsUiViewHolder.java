package com.alg.ailigou.pages.mall.details.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.base.BaseUiViewHolder;
import com.alg.ailigou.common.widget.CustomRecyclerView;
import com.alg.ailigou.common.widget.CustomScrollView;
import com.alg.ailigou.common.widget.CustomWebView;
import com.alg.ailigou.pages.home.widget.YhFlowLayout;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.details.holder
 * Created by Chris Chen on 2017/8/14 13:31.
 * Explain:商品详情页UI持有者
 */

public class GoodsDetailsUiViewHolder extends BaseUiViewHolder{
    //导航部分
    @BindView(R.id.sv_mall_goods_details_container)
    public CustomScrollView svGoodsDetailsContainer;//外部滚动控件
    @BindView(R.id.tb_mall_goods_details_toolbar)
    public LinearLayout tbGoodsDetailsToolBar;//工具条
    @BindView(R.id.iv_mall_goods_details_group_details)
    public ImageView ivGroupDetails;//导航条:详情
    @BindView(R.id.iv_mall_goods_details_group_comment)
    public ImageView ivGroupComment;//导航条:评论
    @BindView(R.id.iv_mall_goods_details_group_recomment)
    public ImageView ivGroupRecomment;//导航条:推荐
    //商品信息部分
    @BindView(R.id.iv_mall_goods_details_image)
    public ImageView ivGoodsImage;//商品大图片
    @BindView(R.id.tv_mall_goods_details_title)
    public TextView tvGoodsTitle;//商品名称
    @BindView(R.id.tv_current_price)
    public TextView tvCurrentPrice; //当前的价格
    @BindView(R.id.tv_mall_goods_details_price_old)
    public TextView tvGoodsOldPrice;//原价
    @BindView(R.id.tv_mall_goods_details_courier)
    public TextView tvGoodsCourier;//快递费
    @BindView(R.id.tv_mall_goods_details_sold_count)
    public TextView tvGoodsSoldCount;//已售出
    @BindView(R.id.tv_mall_goods_details_good_rate)
    public TextView tvGoodsGoodRate;//好评率

    //评论
    @BindView(R.id.tv_mall_goods_details_comment_count)
    public TextView tvCommentCount;//参与评论的人数
    @BindView(R.id.flow_mall_goods_details_comment_lables)
    public YhFlowLayout flowCommentLabels;//评论标签
    @BindView(R.id.rv_mall_goods_details_comment)
    public CustomRecyclerView rvComment;//评论

    //推荐
    @BindView(R.id.rv_mall_goods_details_recomment)
    public CustomRecyclerView rvRecomment;//推荐商品

    @BindView(R.id.web_mall_goods_details_info)
    public CustomWebView webGoodsInfo;//详情





    public
    GoodsDetailsUiViewHolder(View contentView) {
        super(contentView);
    }
}
