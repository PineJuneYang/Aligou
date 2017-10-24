package com.alg.ailigou.pages.home.wine.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.base.adapter.BannerAdapter;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.widget.banner.BannerViewPager;
import com.alg.ailigou.library.widget.banner.ViewPagerAdapter;
import com.alg.ailigou.pages.cart.adapter.BaseVLayoutAdapter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.entrance.holder.GoodsRecommendViewHolder;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.home.wine.holder.WineHeaderHolder;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 海航 * on 2017/7/17.
 * 此类或接口用于  佳酿的整体的recylerview的 adapter  使用了Vlayout
 */

public class WineAdapter extends BaseVLayoutAdapter {

    private OnLoadMoreDataListener onLoadMoreDataListener = new OnLoadMoreDataListener() {
        @Override
        public void onLoadMore() {

        }
    };


    public OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    private OnClickListener mOnClickListener;

    public WineAdapter(List datas, Context context, LayoutHelper helper) {
        super(datas, context, helper);
    }

    public WineAdapter(List datas, Context context, LayoutHelper helper, int type) {
        super(datas, context, helper, type);
    }

    public WineAdapter(List datas, Context context) {

        super(datas, context);
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        MyBaseViewHolder viewHolder = null;
        switch (viewType) {
            case BASE_ITEM_TYPE_BANNER_WIDTH_BG://最上面的 banner
                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_wine_banner, parent, false);
                initBannerViewpager(view, parent);
                viewHolder = new WineHeaderHolder(view, null);
                break;
            case BASE_ITEM_TYPE_HORIZONTAL://横向的recylerview
                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_wine_recylerview, parent, false);
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyler_view);
                //设置布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                WineHorziontalAdapter adapter = new WineHorziontalAdapter(datas, mContext);
                recyclerView.setAdapter(adapter);
                adapter.setListener(new OnItemClickListener() {
                    @Override
                    public void setOnItemClickListener(View view, int position) {
                        if (getListener() != null) {
                            getListener().setOnItemClickListener(view, position);
                        }
                    }
                });
                viewHolder = new MyBaseViewHolder(view, null);
                break;
            case BASE_ITEM_TYPE_HEADER_BANNER://中间的banner 和上面的banner不要太一样
                if (datas != null || datas.size() != 0) {
                    view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_shopping_cart_banner, parent, false);
                    BannerViewPager bannerViewPager = (BannerViewPager) view.findViewById(R.id.bannerview);
                    BannerAdapter pagerAdapter = new BannerAdapter(mContext, datas, 1);
                    bannerViewPager.setAdapter(pagerAdapter);
                    viewHolder = new MyBaseViewHolder(view, null);
                }
                break;
            case BASE_ITEM_TYPE_HEADER_TITLE://四个字  猜你喜欢
                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_shopping_cart_recommend_title, parent, false);
                TextView tv = (TextView) view.findViewById(R.id.tv_recommend_title);
                tv.setText("推荐您喜欢");
                viewHolder = new MyBaseViewHolder(view, null);
                break;
            case BASE_ITEM_TYPE_RECOMMEND://两列的rexcylerview
                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_common_goods_info, parent, false);
//                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_wine_recommend, parent, false);

                viewHolder = new GoodsRecommendViewHolder(view, getListener());
//                RecyclerView recommendRecycleView = (RecyclerView) view.findViewById(R.id.recycleView_home_wine_recommend);
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
//                gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                recommendRecycleView.setLayoutManager(gridLayoutManager);
//                WineRecommendAdapter recommendAdapter = new WineRecommendAdapter(datas,mContext);
//                recommendRecycleView.setAdapter(recommendAdapter);

//                viewHolder = new GoodsRecommendViewHolder(view, getListener());
//                viewHolder = new MyBaseViewHolder(view,null);

                break;
        }
        return viewHolder;
    }

    /**
     * 头布局的处理
     *
     * @param view
     * @param parent
     */
    private void initBannerViewpager(View view, ViewGroup parent) {
        BannerViewPager topBanner = (BannerViewPager) view.findViewById(R.id.bannerview);
        List<View> views = new ArrayList<>();
        Log.d("dugu", datas.size() + "initBannerViewpager");
        for (int i = 0; i < datas.size(); i++) {
            View bannerItem = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_wine_banner_item, parent, false);
            CommodityModel model = (CommodityModel) datas.get(i);
            Glide.with(mContext).load(model.imageUrl).into((ImageView) bannerItem.findViewById(R.id.iv_goods));
            ((TextView) bannerItem.findViewById(R.id.tv_goods_title)).setText(model.title);
            ((TextView) bannerItem.findViewById(R.id.tv_goods_price)).setText("价格:" + model.price);
            views.add(bannerItem);
        }

        BannerAdapter adapter = new BannerAdapter(mContext, views, 1);
        topBanner.setAdapter(adapter);
        adapter.setOnPageClickListener(new ViewPagerAdapter.OnPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                if (getOnClickListener() != null) {
                    getOnClickListener().setOnClickListener(view, position);
                }
            }
        });
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        List<CommodityModel> goods;
        switch (getItemViewType(position)) {
            case BASE_ITEM_TYPE_RECOMMEND://两列的recylerview
                GoodsRecommendViewHolder vh = (GoodsRecommendViewHolder) holder;
                goods = datas;
                vh.getTvPrice().setText("￥:" + goods.get(position).price);
                vh.getTvGoodsDesc().setText(goods.get(position).describe);
//                Glide.with(mContext).load(goods.get(position).imageUrl).into(vh.getIvGoodsInfo());
                ImageLoadUtils.loadSquareImage(mContext,goods.get(position).imageUrl,vh.getIvGoodsInfo());
                setOnItemClickListener(vh, vh.getItemView(), position);//点击事件
                break;

        }
    }
}
