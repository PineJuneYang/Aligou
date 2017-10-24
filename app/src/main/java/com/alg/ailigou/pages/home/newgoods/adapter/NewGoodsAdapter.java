package com.alg.ailigou.pages.home.newgoods.adapter;

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
import com.alg.ailigou.pages.home.newgoods.holder.NewGoodsHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/17.
 * 此类或接口用于
 */

public class NewGoodsAdapter extends BaseHeadAndFootAdapter {

    private List<CommodityModel> datas;

    public NewGoodsAdapter(List datas, Context context) {
        super(datas, context);
        this.datas = datas;
    }


    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alg_item_home_new_goods, parent, false);
        return new NewGoodsHolder(view, getListener());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewGoodsHolder) {
            NewGoodsHolder vh = (NewGoodsHolder) holder;
//            Glide.with(mContext).load(datas.get(position).imageUrl).into(vh.getIvGoods());
            ImageLoadUtils.loadSquareImage(mContext,datas.get(position).imageUrl,vh.getIvGoods(),3,0);
            vh.getTvGoodsPrice().setText("￥:"+datas.get(position).price);
            vh.getTvGoodsTitle().setText(datas.get(position).describe);
        }

    }
}
