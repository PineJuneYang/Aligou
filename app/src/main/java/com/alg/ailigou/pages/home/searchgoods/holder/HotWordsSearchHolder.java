package com.alg.ailigou.pages.home.searchgoods.holder;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.utils.DisplayUtils;
import com.alg.ailigou.library.utils.DrawableUtils;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.widget.YhFlowLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/7/31
 * 此类或接口用于
 */

public class HotWordsSearchHolder extends MyBaseViewHolder{


    @BindView(R.id.flowLayoutItem)
    YhFlowLayout flowLayoutItem;


    private Context context;

    public HotWordsSearchHolder(View itemView, OnItemClickListener listener,Context context) {
        super(itemView, listener);
        this.context = context;
    }





    private void displayUI(List<String> hotGoods, YhFlowLayout flowLayout) {

        for (int i = 0; i < hotGoods.size(); i++) {
            final String data = hotGoods.get(i);
            TextView tv = new TextView(context);
            tv.setText(data);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            tv.setGravity(Gravity.CENTER);
            int paddingy = DisplayUtils.dp2Px(context, 7);
            int paddingx = DisplayUtils.dp2Px(context, 6);
            tv.setPadding(paddingx, paddingy, paddingx, paddingy);
            tv.setClickable(false);

            int shape = GradientDrawable.RECTANGLE;
            int radius = DisplayUtils.dp2Px(context, 4);
            int strokeWeight = DisplayUtils.dp2Px(context, 1);
            int stokeColor = context.getResources().getColor(R.color.alg_cart_price_accounts);
            int stokeColor2 = context.getResources().getColor(R.color.alg_cart_price_accounts);

            GradientDrawable normalBg = DrawableUtils.getShape(shape, radius, strokeWeight, stokeColor, Color.WHITE);
            GradientDrawable pressedBg = DrawableUtils.getShape(shape, radius, strokeWeight, stokeColor2, context.getResources().getColor(R.color.alg_cart_price_accounts));
            StateListDrawable selector = DrawableUtils.getSelector(normalBg, pressedBg);
            tv.setBackgroundDrawable(selector);
            ColorStateList colorStateList = DrawableUtils.getColorSelector(context.getResources().getColor(R.color.alg_cart_price_accounts), context.getResources().getColor(R.color.alg_city_common_bg));
            tv.setTextColor(colorStateList);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    etSearchInput.setText(data);
//
//                    Intent intent = new Intent(SearchGoodsActivity.this,SearchDetailActivity.class);
//                    intent.putExtra("SearchHotWords",data);
//                    startActivity(intent);
                }
            });
            flowLayout.addView(tv);
        }
    }

    public void setData(List<String> hotWords) {
        displayUI(hotWords,flowLayoutItem);

    }
}
