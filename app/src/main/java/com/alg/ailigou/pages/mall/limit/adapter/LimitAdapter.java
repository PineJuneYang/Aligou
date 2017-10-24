package com.alg.ailigou.pages.mall.limit.adapter;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.ChoiceCheapGoodsDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.utils.TimeUtils;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.mall.limit.holder.LimitHeadHolder;
import com.alg.ailigou.pages.mall.limit.holder.LimitHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/21.
 * 此类或接口用于  限时特卖
 */

public class LimitAdapter extends BaseHeadAndFootAdapter {

    private List<CommodityModel> datas;

    public LimitAdapter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }

    private ChoiceCheapGoodsDataModel mDataModel;

    public void setDataModel(ChoiceCheapGoodsDataModel dataModel) {
        mDataModel = dataModel;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_mall_week_new, parent, false);
        return new LimitHolder(view, listener, getOnClickListener(), mContext);
    }

    @Override
    protected MyBaseViewHolder onHeadViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = (View) mHeaderViews.get(BASE_ITEM_TYPE_HEADER);
        return new LimitHeadHolder(view, null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BASE_ITEM_TYPE_HEADER) {
            if (mDataModel!=null){
                //todo  这里暂时使用暂定的url,方便调试ui,因为真实的url还没数据
                Glide.with(mContext).load("http://192.168.1.107/upload/images/alg_home_header_action_banner_03.jpg").into(((LimitHeadHolder) holder).getIvMallBanner());

//                ImageLoadUtils.load(mContext,mDataModel.mBannerModel.image,((LimitHeadHolder) holder).getIvMallBanner());

            }
        } else {
            if (holder instanceof LimitHolder){
                LimitHolder viewHolder = (LimitHolder) holder;
                Glide.with(mContext).load("http://192.168.1.107/upload/images/bag.jpg").into(viewHolder.getIvGoods());

                //todo  这里暂时使用暂定的url,方便调试ui,因为真实的url还没数据
//                ImageLoadUtils.load(mContext,datas.get(position - 1).imageUrl,viewHolder.getIvGoods());
                viewHolder.getTvGoodsTitle().setText(datas.get(position - 1).title);
                viewHolder.getTvNowPrice().setText("￥:" + datas.get(position - 1).price);
                viewHolder.getTvOldPrice().setText("￥:" + datas.get(position - 1).oldPrice);
                if (datas.get(position - 1).countDown==0){
                    datas.get(position - 1).countDown= SystemClock.currentThreadTimeMillis();
                }
                viewHolder.getTvTime().setText("倒计时:"+ TimeUtils.format( datas.get(position - 1).countDown, TimeUtils.PATTERN5));
            }

        }

    }


}
