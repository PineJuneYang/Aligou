package com.alg.ailigou.pages.mall.weeknew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.holder.CommonImgBannerHolder;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.widget.AutoImageView;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.mall.weeknew.holder.MallWeekNewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/21.
 * 此类或接口用于  每周上新  还有那个限时特卖  通用的item的  viewholder
 */

public class WeekNewAdapter extends BaseHeadAndFootAdapter {



    private List<CommodityModel> datas;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private int visibleThreshold = 2;
    private int itemCount;

    private BannerModel bannerModel = new BannerModel();

    public void setBannerModel(BannerModel mbannerModel) {
        this.bannerModel = mbannerModel;
    }

    private OnLoadMoreDataListener onLoadMoreDataListener;
    private OnClickListener clickListener;

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setOnLoadMoreDataListener(OnLoadMoreDataListener onLoadMoreDataListener) {
        this.onLoadMoreDataListener = onLoadMoreDataListener;
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public WeekNewAdapter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;


    }


    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_week_new, parent, false);

        return new MallWeekNewHolder(view, listener, mContext);
        // return new LimitHolder(view, listener, getOnClickListener(), mContext);

    }

    @Override
    protected MyBaseViewHolder onHeadViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = (View) mHeaderViews.get(BASE_ITEM_TYPE_HEADER);
//        View headerView = LayoutInflater.from(mContext).inflate(R.layout.alg_inc_common_image, null);
        return new CommonImgBannerHolder(view, listener);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BASE_ITEM_TYPE_HEADER) {

//            if (bannerModel.image != null) {
//                Glide.with(mContext).load(bannerModel.image).into(((CommonImgBannerHolder) holder).getIvBanner());
//            } else {
//                Glide.with(mContext).load("").into(((CommonImgBannerHolder) holder).getIvBanner());
//            }
            Glide.with(mContext).load(bannerModel.image).into(((CommonImgBannerHolder) holder).getIvBanner());


        } else {
            if (holder instanceof MallWeekNewHolder) {
                MallWeekNewHolder mallWeekNewHolder = (MallWeekNewHolder) holder;
                mallWeekNewHolder.setData(datas.get(position - 1));
            }

        }

    }


}
