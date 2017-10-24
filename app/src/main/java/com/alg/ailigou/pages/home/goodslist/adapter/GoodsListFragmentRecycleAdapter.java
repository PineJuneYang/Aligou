package com.alg.ailigou.pages.home.goodslist.adapter;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.pages.home.goodslist.holder.GoodsListViewHolder;
import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/7/28
 * 此类或接口用于
 */

public class GoodsListFragmentRecycleAdapter extends BaseRecyclerAdapter<GoodsListViewHolder> {



    private LayoutInflater inflater;
    private Context mContext;
    private List<CommodityModel> commodityModels;


    private int COMMON_VIEW_TYPE = 0;

    public GoodsListFragmentRecycleAdapter(Context context, List<CommodityModel> commodityModels) {

        this.mContext = context;
        this.inflater = LayoutInflater.from(context);

        this.commodityModels = commodityModels;
    }


    @Override
    public GoodsListViewHolder getViewHolder(View view) {
        return new GoodsListViewHolder(view, false, mContext);
    }

    @Override
    public GoodsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {

        View itemView = inflater.inflate(R.layout.alg_item_home_goods_list, null, false);

        return new GoodsListViewHolder(itemView, true, mContext);
    }

    @Override
    public void onBindViewHolder(GoodsListViewHolder holder, int position, boolean isItem) {
        holder.setData(commodityModels.get(position));

    }

    @Override
    public int getAdapterItemCount() {
        return commodityModels.size();
    }


    @Override
    public int getAdapterItemViewType(int position) {
        return COMMON_VIEW_TYPE;
    }


}
