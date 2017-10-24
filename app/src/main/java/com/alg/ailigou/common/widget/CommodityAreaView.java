package com.alg.ailigou.common.widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityAreaModel;
import com.alg.ailigou.common.model.HomeCommodityTypeModel;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.pages.home.everydaycheap.EveryDayCheapActivity;
import com.alg.ailigou.pages.home.hotsaleweek.HotSaleWeekActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * AiligouApp
 * com.alg.ailigou.common.widget
 * Created by Chris Chen on 2017/7/10 09:56.
 * Explain:
 */

public class CommodityAreaView extends LinearLayout {
    @BindView(R.id.tv_home_commodity_area_panic_buying_title)
    TextView mTvPanicBuyingTitle;
    @BindView(R.id.iv_home_commodity_area_panic_buying_image)
    ImageView mIvPanicBuyingImage;
    @BindView(R.id.tv_home_commodity_area_everyday_sales_title)
    TextView mTvEverydaySalesTitle;
    @BindView(R.id.iv_home_commodity_area_everyday_sales_image)
    ImageView mIvEverydaySalesImage;
    @BindView(R.id.tv_home_commodity_area_week_hot_title)
    TextView mTvWeekHotTitle;
    @BindView(R.id.iv_home_commodity_area_week_hot_image)
    ImageView mIvWeekHotImage;

    private CommodityAreaModel commodityAreaModel;


    public CommodityAreaView(Context context) {
        super(context, null);
    }

    public CommodityAreaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.alg_inc_home_action_area, this);
        ButterKnife.bind(this);
    }


    public CommodityAreaModel getCommodityAreaModel() {
        return commodityAreaModel;
    }

    public void setCommodityAreaModel(CommodityAreaModel commodityAreaModel) {

        if (commodityAreaModel.commodityTypeList.size() >= 3) {//本区域需要至少三条数据
            this.commodityAreaModel = commodityAreaModel;
            fillData(commodityAreaModel.commodityTypeList);
        }
    }

    /**
     * 为控件填充数据
     *
     * @param commodityTypeList
     */
    private void fillData(List<HomeCommodityTypeModel> commodityTypeList) {
        //限时抢购
        mTvPanicBuyingTitle.setText(commodityTypeList.get(0).title);
        ImageUtils.load(getContext(), commodityTypeList.get(0).imageUrl, mIvPanicBuyingImage);
        //天天特价
        mTvEverydaySalesTitle.setText(commodityTypeList.get(1).title);
        ImageUtils.load(getContext(), commodityTypeList.get(1).imageUrl, mIvEverydaySalesImage);
        //本周热卖
        mTvWeekHotTitle.setText(commodityTypeList.get(2).title);
        ImageUtils.load(getContext(), commodityTypeList.get(2).imageUrl, mIvWeekHotImage);
    }

    @OnClick({R.id.iv_home_commodity_area_panic_buying_image,
            R.id.iv_home_commodity_area_everyday_sales_image,
            R.id.iv_home_commodity_area_week_hot_image})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_home_commodity_area_panic_buying_image:

                break;
            case R.id.iv_home_commodity_area_everyday_sales_image:
                intent = new Intent(getContext(), EveryDayCheapActivity.class);
                break;
            case R.id.iv_home_commodity_area_week_hot_image:
                intent = new Intent(getContext(), HotSaleWeekActivity.class);
                break;
        }
        if (intent != null) {
            getContext().startActivity(intent);
        }
    }
}
