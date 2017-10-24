package com.alg.ailigou.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.utils.MessageUtils;
import com.alg.ailigou.pages.home.entrance.adapter.CommodityAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AiligouApp
 * com.alg.ailigouapp.common.widget
 * Created by Chris Chen on 2017/7/6 15:05.
 * Explain:商品专区View
 */

public class CommodityZoneView extends LinearLayout {
    private ViewHolder viewHolder;
    private List<CommodityModel> commodityModelList;

    public CommodityZoneView(Context context) {
        super(context, null);
    }

    public CommodityZoneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.alg_item_home_resommend, this);
        viewHolder = new ViewHolder(this);
    }

    public List<CommodityModel> getCommodityModelList() {
        return commodityModelList;
    }

    public void setCommodityModelList(List<CommodityModel> commodityModelList) {
        this.commodityModelList = commodityModelList;
        //填充下面的滚动商品列表
        final CommodityAdapter adapter = new CommodityAdapter(getContext());
        viewHolder.rvCommoditySecondList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        viewHolder.rvCommoditySecondList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                MessageUtils.debug(adapter.getItem(position).title);
            }
        });
        adapter.updateData(commodityModelList);
    }

    static class ViewHolder {
        @BindView(R.id.tv_commodity_zone_title)
        TextView tvCommodityZoneTitle;
        @BindView(R.id.tv_commodity_zone_1_title)
        TextView tvCommodityZone1Title;
        @BindView(R.id.iv_commodity_zone_1_image)
        ImageView ivCommodityZone1Image;
        @BindView(R.id.tv_commodity_zone_2_title)
        TextView tvCommodityZone2Title;
        @BindView(R.id.iv_commodity_zone_2_image)
        ImageView ivCommodityZone2Image;
        @BindView(R.id.tv_commodity_zone_3_title)
        TextView tvCommodityZone3Title;
        @BindView(R.id.iv_commodity_zone_3_image)
        ImageView ivCommodityZone3Image;
        @BindView(R.id.tv_commodity_zone_4_title)
        TextView tvCommodityZone4Title;
        @BindView(R.id.iv_commodity_zone_4_image)
        ImageView ivCommodityZone4Image;
        @BindView(R.id.rv_commodity_second_list)
        RecyclerView rvCommoditySecondList;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
