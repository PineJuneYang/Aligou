package com.alg.ailigou.pages.home.self.adapter;

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
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.self.holder.SelfHeaderHolder;
import com.alg.ailigou.pages.home.self.holder.SelfHolder;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于
 */

public class SelfAdapter extends BaseHeadAndFootAdapter {

    List<CommodityModel> datas;


    public SelfAdapter(List<CommodityModel> datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_self_classic, parent, false);
        return new SelfHolder(view, listener);
    }

    @Override
    protected MyBaseViewHolder onHeadViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = (View) mHeaderViews.get(BASE_ITEM_TYPE_HEADER);
        return new SelfHeaderHolder(view, null, getOnClickListener());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BASE_ITEM_TYPE_HEADER) {

        } else {
            if (holder instanceof SelfHolder) {
                SelfHolder vh = (SelfHolder) holder;
//
                ImageLoadUtils.load(mContext,datas.get(position - getHeadersCount()).imageUrl2,vh.getIvSelfClassicImage());
                vh.getTitle().setText(datas.get(position - getHeadersCount()).title);
                vh.getTvSelfClassicPrice().setText("￥"+datas.get(position - getHeadersCount()).price);
            }

        }
    }


}
