package com.alg.ailigou.pages.news.fragments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.news.fragments.holder.NewsItemNormalHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/13.
 * 此类或接口用于  新闻的adapter
 */

public class NewsAdapter extends BaseHeadAndFootAdapter {
    private List<NewsModel> datas;

    public NewsAdapter(List<NewsModel> datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        switch ((datas.get(position)).style) {
            case 0:
                type = 0;//这个就是一张图片
                break;
            case 1:
                type = 1;//这个是 一张图 ,然后描述信息等
                break;
            case 2:
                type = 2;//这个是三张图片的列表
                break;
            case 3:
                type = 4;//这个是一个标题一个大图片
                break;
        }
        return type;
    }


    @Override
    public MyBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyBaseViewHolder holder = null;
        View view;
//        switch (viewType) {
//            case 0://这个是 只有一张图
//                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_news_title_image, parent, false);
//                holder = new NewsItemTitleImageHolder(view, getListener());
//                break;
//            case 1://这个是 一张图 ,然后描述信息等
        view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_news_normal, parent, false);
        holder = new NewsItemNormalHolder(view, getListener());
//                break;
//            case 2://这个是三张图片的列表
//                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_news_there_images, parent, false);
//                holder = new NewsItemThereIamgeHolder(view, getListener());
//                break;
//            case 3://这个是一个标题一个大图片
//                view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_news_title_width_image, parent, false);
//                holder = new NewsItemTitleWidthImageHolder(view, getListener());
//                break;
//        }
        return holder;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        switch (getItemViewType(position)) {
//            case 0://只有一张图片的item
//                NewsItemTitleImageHolder imageHolder = (NewsItemTitleImageHolder) holder;
//                Glide.with(mContext).load(datas.get(position).imageUrl01).into(imageHolder.getIv());
//                break;
//            case 1://这个是 一张图 ,然后描述信息等
        NewsItemNormalHolder normalHolder = (NewsItemNormalHolder) holder;
        Glide.with(mContext).load(datas.get(position).imageUrl01).into(normalHolder.getIvNews());
        normalHolder.getTvNewsDesc().setText(datas.get(position).describe);
        normalHolder.getTvTime().setText(datas.get(position).time + "");
//                break;
//            case 2://这个是一个标题一个大图片
//                NewsItemThereIamgeHolder thereIamgeHolder = (NewsItemThereIamgeHolder) holder;
//                Glide.with(mContext).load(datas.get(position).imageUrl01).into(thereIamgeHolder.getImage1());
//                Glide.with(mContext).load(datas.get(position).imageUrl02).into(thereIamgeHolder.getImage2());
//                Glide.with(mContext).load(datas.get(position).imageUrl03).into(thereIamgeHolder.getImage3());
//                break;
//            case 3://这个是一个标题一个大图片
//                NewsItemTitleWidthImageHolder titleWidthImageHolder = (NewsItemTitleWidthImageHolder) holder;
//                Glide.with(mContext).load(datas.get(position).imageUrl01).into(titleWidthImageHolder.getIvGoods());
//                titleWidthImageHolder.getTvTitle().setText(datas.get(position).title);
//                break;
        //       }
    }


}
