package com.alg.ailigou.pages.home.hotrecommend.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/21.
 * 此类或接口用于
 */

public class HotRecommendHeaderHolder extends MyBaseViewHolder {
    OnClickListener headerListener;

    @BindView(R.id.iv_home_recommend_icon_one)
    ImageView ivHomeRecommendIconOne;
    @BindView(R.id.tv_home_recommend_brand_one)
    TextView tvHomeRecommendBrandOne;
    @BindView(R.id.tv_home_recommend_now_price_one)
    TextView tvHomeRecommendNowPriceOne;
    @BindView(R.id.tv_home_recommend_old_price_one)
    TextView tvHomeRecommendOldPriceOne;
    @BindView(R.id.iv_home_recommend_icon_two)
    ImageView ivHomeRecommendIconTwo;
    @BindView(R.id.tv_home_recommend_brand_two)
    TextView tvHomeRecommendBrandTwo;
    @BindView(R.id.tv_home_recommend_now_price_two)
    TextView tvHomeRecommendNowPriceTwo;
    @BindView(R.id.tv_home_recommend_old_price_two)
    TextView tvHomeRecommendOldPriceTwo;

    private Context mcontext;


    public HotRecommendHeaderHolder(View itemView, OnItemClickListener listener, OnClickListener headerListener,Context context) {
        super(itemView, listener);
        this.headerListener = headerListener;
        this.mcontext = context;
    }

    @OnClick({R.id.ll_recommend_head_1, R.id.ll_recommend_head_2})
    public void onViewClicked(View view) {
        if (headerListener != null) {
            headerListener.setOnClickListener(view, getAdapterPosition());
        }

    }

    public void setData(List<CommodityModel> cheapGoods) {
        ImageLoadUtils.load(mcontext,cheapGoods.get(0).imageUrl,ivHomeRecommendIconOne);
        tvHomeRecommendBrandOne.setText(cheapGoods.get(0).brand);
        tvHomeRecommendNowPriceOne.setText("￥:"+cheapGoods.get(0).price);
        tvHomeRecommendOldPriceOne.setText("￥:"+cheapGoods.get(0).oldPrice);

        ImageLoadUtils.load(mcontext,cheapGoods.get(1).imageUrl,ivHomeRecommendIconTwo);
        tvHomeRecommendBrandTwo.setText(cheapGoods.get(1).brand);
        tvHomeRecommendNowPriceTwo.setText("￥:"+cheapGoods.get(1).price);
        tvHomeRecommendOldPriceTwo.setText("￥:"+cheapGoods.get(1).oldPrice);


    }
}
