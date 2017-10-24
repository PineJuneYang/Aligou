package com.alg.ailigou.pages.mall.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommentLabelModel;
import com.alg.ailigou.common.model.ConstructCommentLabelModel;
import com.alg.ailigou.library.base.activity.BaseActivity;
import com.alg.ailigou.library.manager.UtilsManager;
import com.alg.ailigou.library.utils.DisplayUtils;
import com.alg.ailigou.library.utils.DrawableUtils;
import com.alg.ailigou.pages.home.consts.HomeConsts;
import com.alg.ailigou.pages.home.widget.YhFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.utils
 * Created by Chris Chen on 2017/8/2 16:47.
 * Explain:商城模块的工具类
 */

public class MallUtils extends UtilsManager implements HomeConsts{
    /**
     * 商品详情页评论流式布局处理
     * @param hotGoods
     * @param flowLayout
     */
    public static void setupFlowLayout(Context context, CommentLabelModel hotGoods, YhFlowLayout flowLayout, int textColor, int horizontalSpac, int verticalSpac) {
        flowLayout.setSpace(horizontalSpac,verticalSpac);


        for (int i = 0; i < HOME_COMMENT_LABEL.length; i++) {
            String data = HOME_COMMENT_LABEL[i];
            int count  = 0;
            switch (i){
                case 0:
                    count  = hotGoods.allCount;
                    break;
                case 1:
                    count  = hotGoods.imgCount;
                    break;
                case 2:
                    count = hotGoods.levelTwo;
                    break;
                case 3:
                    count = hotGoods.levelThree;
                    break;
                case 4:
                    count = hotGoods.levelFour;
                    break;
                case 5:
                    count = hotGoods.levelFive;
                    break;

            }
            TextView tv = new TextView(context);
            tv.setTextColor(context.getResources().getColor(textColor));
            tv.setText(data+"("+count+")");
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            tv.setGravity(Gravity.CENTER);
            int paddingy = DisplayUtils.dp2Px(context,5);
            int paddingx = DisplayUtils.dp2Px(context, 11);
            tv.setPadding(paddingx, paddingy, paddingx, paddingy);
            tv.setClickable(false);

//            int shape = GradientDrawable.RECTANGLE;
//            int radius = DisplayUtils.dp2Px(context, 4);
//            int strokeWeight = DisplayUtils.dp2Px(context, 1);
//            int stokeColor = context.getResources().getColor(R.color.alg_cart_price_accounts);
//            int stokeColor2 = context.getResources().getColor(R.color.alg_cart_price_accounts);
//
//            GradientDrawable normalBg = DrawableUtils.getShape(shape, radius, strokeWeight, stokeColor, Color.WHITE);
//            GradientDrawable pressedBg = DrawableUtils.getShape(shape, radius, strokeWeight, stokeColor2, context.getResources().getColor(R.color.alg_cart_price_accounts));
//            StateListDrawable selector = DrawableUtils.getSelector(normalBg, pressedBg);
//            tv.setBackgroundDrawable(selector);
//            ColorStateList colorStateList = DrawableUtils.getColorSelector(context.getResources().getColor(R.color.alg_cart_price_accounts), context.getResources().getColor(R.color.alg_city_common_bg));
//            tv.setTextColor(colorStateList);
//            tv.setTextColor(context.getResources().getColor(R.color.alg_city_gray));
            if (i==0){
                tv.setBackground(context.getResources().getDrawable(R.drawable.alg_common_shape_rectangle_high_grey_circle));
            }else {

                tv.setBackground(context.getResources().getDrawable(R.drawable.alg_common_shape_rectangle_grey_circle));
            }
            flowLayout.addView(tv);
        }
    }

    /**
     * 设置窗口背景透明
     * @param bgAlpha
     */
    public static void setBackgroundAlpha(Context context,float bgAlpha) {
        WindowManager.LayoutParams lp = ((BaseActivity) context).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((BaseActivity) context).getWindow().setAttributes(lp);
    }




}
