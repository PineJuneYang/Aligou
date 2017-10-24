package com.alg.ailigou.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AiligouApp
 * com.alg.ailigouapp.common.widget
 * Created by Chris Chen on 2017/7/6 15:40.
 * Explain:商品专区次要商品列表项
 */

public class CommoditySecondView extends LinearLayout {
    @BindView(R.id.iv_commodity_zone_second_image)
    public ImageView ivImage;
    @BindView(R.id.tv_commodity_zone_second_title)
    public TextView tvTitle;

    private String image,title;
    private CommodityModel commodityModel;

    public CommoditySecondView(Context context) {
        super(context, null);
    }

    public CommoditySecondView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.alg_item_home_recommend_second, this);
        ButterKnife.bind(this);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        ImageUtils.load(getContext(),image,ivImage);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        tvTitle.setText(title);
    }

    public CommodityModel getCommodityModel() {
        return commodityModel;
    }

    public void setCommodityModel(CommodityModel commodityModel) {
        this.commodityModel = commodityModel;
        ImageUtils.load(getContext(),commodityModel.imageUrl,ivImage);
        tvTitle.setText(commodityModel.title);
    }
}
