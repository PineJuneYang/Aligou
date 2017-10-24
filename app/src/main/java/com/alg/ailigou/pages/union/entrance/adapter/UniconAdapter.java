package com.alg.ailigou.pages.union.entrance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.union.entrance.holder.UniconHolder;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/26.
 * 此类或接口用于
 */

public class UniconAdapter extends BaseHeadAndFootAdapter {

    private List<UnionModel> datas;

    public UniconAdapter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_business, parent, false);
        return new UniconHolder(view, getListener());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UniconHolder) {
            UniconHolder vh = (UniconHolder) holder;
            ImageLoadUtils.load(mContext, datas.get(position).imgUrl, vh.getIvHomeBusinessImage());
            vh.getTvHomeBusinessAddress().setText(datas.get(position).address);
            vh.getTvHomeBusinessContactsName().setText(datas.get(position).person);
            vh.getTvHomeBusinessContactsPhone().setText(datas.get(position).telNumber);
        }
    }
}
