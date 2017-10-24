package com.alg.ailigou.pages.home.algchoice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.HomeCommodityTypeModel;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.algchoice.holder.AlgChoiceHeaderHolder;
import com.alg.ailigou.pages.home.algchoice.holder.AlgChoiceNormalHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/20.
 * 此类或接口用于
 */

public class AlgChoiceAdapter extends BaseHeadAndFootAdapter {


    private List<CommodityModel> datas;
    private List<HomeCommodityTypeModel> topdata;


    private OnClickListener mOnClickListener;

    public AlgChoiceAdapter(List<CommodityModel> datas, Context context, List<HomeCommodityTypeModel> topdata) {
        super(datas, context);
        this.datas = datas;
    }


    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_alg_choice_normal, parent, false);
        return new AlgChoiceNormalHolder(view, listener);
    }

    @Override
    protected MyBaseViewHolder onHeadViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        return new AlgChoiceHeaderHolder((View) mHeaderViews.get(BASE_ITEM_TYPE_HEADER + 0), null, getOnClickListener());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            AlgChoiceHeaderHolder viewHolder = (AlgChoiceHeaderHolder) holder;
        } else {
            if (holder instanceof AlgChoiceNormalHolder){
                AlgChoiceNormalHolder vh = (AlgChoiceNormalHolder) holder;
                Glide.with(mContext).load(datas.get(position-1).imageUrlBig).into(vh.getIvGoods());
                vh.getTvBrandName().setText(datas.get(position-1).brand);
                vh.getTvPlan().setText(datas.get(position-1).copy);
            }

        }
    }


}
