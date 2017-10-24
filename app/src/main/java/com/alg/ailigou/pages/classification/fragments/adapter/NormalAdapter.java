package com.alg.ailigou.pages.classification.fragments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.classification.fragments.holder.NormalHolder;

import java.util.List;

/**
 * Created by 玖泞
 * on 2017/8/2
 * 此类或接口用于
 */

public class NormalAdapter extends BaseHeadAndFootAdapter {


    private LayoutInflater layoutInflater;


    public NormalAdapter(List datas, Context context) {
        super(datas, context);

        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {

        return new NormalHolder(layoutInflater.inflate(R.layout.alg_item_classification_normal, null), listener);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalHolder) {
            NormalHolder normalHolder = (NormalHolder) holder;

            ImageLoadUtils.load(mContext, ((CommodityTypeModel) datas.get(position)).imageUrl, normalHolder.getIvHotImage());
            normalHolder.getTvHotTitle().setText(((CommodityTypeModel) datas.get(position)).title);
        }

    }
}
