package com.alg.ailigou.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.pages.mall.utils.MallUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AiligouApp
 * com.alg.ailigou.common.widget
 * Created by Chris Chen on 2017/7/11 17:05.
 * Explain:商品详情页加入购物车的泡泡窗口
 */

public class GoodsPopupWindow extends PopupWindow {
    @BindView(R.id.iv_mall_details_popup_buy_goods_close)
    ImageView close;
    @BindView(R.id.tv_mall_details_popup_buy_goods_title)
    TextView goodsTitle;
    @BindView(R.id.tv_mall_details_popup_buy_goods_stock)
    TextView goodsStock;
    @BindView(R.id.tv_mall_details_popup_buy_goods_price)
    TextView goodsPrice;
    @BindView(R.id.iv_mall_details_popup_buy_goods_image)
    ImageView goodsImage;
    @BindView(R.id.tv_mall_details_popup_buy_goods_ok)
    TextView goodsOk;
    @BindView(R.id.rl_goods_info)
    RelativeLayout rlGoodsInfo;
    @BindView(R.id.ll_add_cart_property)
    LinearLayout llAddCartProperty;
    private Context mContext;

    private CommodityModel goods;

    public GoodsPopupWindow(Context context, int width, int height) {
        super(width, height);
        mContext = context;
        initView();
    }

    public GoodsPopupWindow(Context context) {
        super(context);
        mContext = context;
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        initView();
    }

    private void initView() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.alg_act_mall_details_popup_buy, null);
        ButterKnife.bind(this, contentView);
        setContentView(contentView);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置背景透明
        setOutsideTouchable(true);
        setFocusable(true);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }

    @Override
    public void dismiss() {
        MallUtils.setBackgroundAlpha(mContext, 1f);
        super.dismiss();
    }

    public void show(View parentView, CommodityModel goods, List<String> propertys) {
//        goodsTitle.setText(goods.title);
//        goodsPrice.setText("￥"+goods.price);
//        goodsStock.setText(goods.stock+"+");
        MallUtils.setBackgroundAlpha(mContext, 0.2f);
        if (propertys != null) {
            int lines = propertys.size() / 4;
            int remainder = propertys.size() % 4;
            if (remainder != 0) {
                lines += 1;
            }
            for (int i = 0; i < lines; i++) {
                LinearLayout linearLayout = new LinearLayout(mContext);
                LinearLayout.LayoutParams linLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
                for (int j = 0; j < 4; j++) {
                    TextView textView = new TextView(mContext);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                    params.setMargins(10, 10, 0, 10);
                    textView.setLayoutParams(params);
                    textView.setText(propertys.get(i * 3 + j));
//                    textView.setPadding(10, 5, 10, 5);
                    textView.setTextColor(mContext.getResources().getColor(R.color.alg_common_bg_dark));

                    if (i == 0 && j == 0) {

                        textView.setBackground(mContext.getResources().getDrawable(R.drawable.alg_common_shape_rectangle_red));
                    } else {

                        textView.setBackground(mContext.getResources().getDrawable(R.drawable.alg_common_shape_rectangle_grey_circle));
                    }
                    linearLayout.addView(textView);
                }
                linearLayout.setLayoutParams(linLayoutParams);
                llAddCartProperty.addView(linearLayout);
            }

        }
        showAtLocation(parentView, Gravity.LEFT + Gravity.BOTTOM, 0, 0);
    }

    private void fillData() {
        if (goods != null) {
            goodsTitle.setText(goods.title);
            goodsPrice.setText("￥" + goods.price);
            goodsStock.setText(9999 + "");
            ImageUtils.load(mContext, goods.imageUrl, goodsImage);
        }


    }
}
