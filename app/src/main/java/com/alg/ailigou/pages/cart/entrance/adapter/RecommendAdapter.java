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
import com.alg.ailigou.pages.cart.entrance.holder.GoodsRecommendViewHolder;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/9/19
 * 此类或接口用于
 */

public class RecommendAdapter extends BaseHeadAndFootAdapter {
    public RecommendAdapter(List datas, Context context) {
        super(datas, context);
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View recommendView = LayoutInflater.from(mContext).inflate(R.layout.alg_item_common_goods_info, parent, false);
        return new GoodsRecommendViewHolder(recommendView,listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof GoodsRecommendViewHolder){
            GoodsRecommendViewHolder vh = (GoodsRecommendViewHolder) holder;
            ImageLoadUtils.loadSquareImage(mContext,((CommodityModel) datas.get(position)).imageUrl,vh.getIvGoodsInfo());
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

        }


    }
}
