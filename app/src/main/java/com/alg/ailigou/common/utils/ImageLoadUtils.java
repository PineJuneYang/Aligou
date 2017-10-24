package com.alg.ailigou.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.alg.ailigou.common.consts.AppConsts;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.library.utils.MeasureUtils;
import com.alg.ailigou.library.utils.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * AiligouApp
 * com.alg.ailigou.common.utils
 * Created by Chris Chen on 2017/8/22 14:13.
 * Explain:
 */

public class ImageLoadUtils extends ImageUtils implements AppConsts {
    /**
     * 加载连接图片服务器的图片
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    public static void load(Context context, String imgUrl, ImageView imageView) {
        ImageUtils.load(context, getImageUrl(imgUrl), imageView);

    }

    /**
     * 加载连接图片服务器为正方形
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    public static void loadSquareImage(Context context, String imgUrl, ImageView imageView) {
        loadSquareImage(context, imgUrl, imageView, 2, 10);
    }

    /**
     * @param context
     * @param imgUrl
     * @param imageView
     * @param count     每行几个item
     * @param spacing   两个item的间距是多少
     */
    public static void loadSquareImage(Context context, String imgUrl, ImageView imageView, int count, int spacing) {

        Glide.with(context).load(getImageUrl(imgUrl))
                .bitmapTransform(new CropTransformation(context, ScreenUtils.getWidth() / count - MeasureUtils.dp2px(spacing), ScreenUtils.getWidth() / count - MeasureUtils.dp2px(spacing), CropTransformation.CropType.CENTER))
                .into(imageView);


    }

    /**
     * 设置圆形图片背景
     */
    public static void setImageCircle(Context context, String imgUrl, ImageView imageView) {
        Glide.with(context)
                .load(getImageUrl(imgUrl))
                .centerCrop()
                .dontAnimate()
                .bitmapTransform(new CropCircleTransformation(context)) //使用圆形变换，还可以使用其他的变换
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 设置圆角矩形
     */
    public static void setRoundedRectangleImage(Context context, String imgUrl, int radius, ImageView imageView) {
        Glide.with(context)
                .load(getImageUrl(imgUrl))
                .bitmapTransform(new RoundedCornersTransformation(context, MeasureUtils.dp2px(radius), 0, RoundedCornersTransformation.CornerType.ALL))
                .into(imageView);
    }

    private static String getImageUrl(String url) {
        if (url != null) {
            if (url.startsWith("http")||(new File(url)).length()>0) {
                return url;
            } else {
                return AILIGOU_APP_IMAGE_SERVER_URL + url;
            }
        } else {
            return url;
        }
    }

}
