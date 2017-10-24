package com.alg.ailigou.common.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.widget.banner.ViewPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class BannerAdapter<T> extends ViewPagerAdapter {

    /**
     * @param context
     * @param data
     * @param countType 0表示 只有几个,譬如 4个  滚到d到4的时候  往左边到1  继续滚动    1表示无限循环
     */
    public BannerAdapter(Context context, List data, int countType) {
        super(context, data, countType);
    }

    @Override
    public View myInstantiateItem(ViewGroup container, int position) {
        if (data == null || data.size() == 0) {
            return null;
        }
        View view = null;
        int i = position;
        switch (countType) {
            case 0://只有几个
                if (data.get(i) instanceof View) {
                    view = (View) data.get(position);
                } else if (data.get(i) instanceof BannerModel) {
                    // TODO: 2017/7/5  这个是我们项目封装的  bean  如果没有可以换了
                    view = new ImageView(context);
                    setScaleType((ImageView) view);
                    ImageLoadUtils.load(context,((BannerModel) data.get(i)).image,(ImageView) view);
                } else {//传过来的是  id了或者   url了
                    i = position % data.size();
                    view = new ImageView(context);
                    // TODO: 2017/7/4  加载图片的方法   使用glide
                    setScaleType((ImageView) view);
                    ImageLoadUtils.load(context,((BannerModel) data.get(i)).image,(ImageView) view);
                }
                break;
            case 1://无限循环
                i = position % data.size();
                if (data.get(i) instanceof View) {
                    view = (View) data.get(i);
                } else if (data.get(i) instanceof BannerModel) {
                    // TODO: 2017/7/5  这个是我们项目封装的  bean  如果没有可以换了
                    view = new ImageView(context);
                    setScaleType((ImageView) view);
                    ImageLoadUtils.load(context,((BannerModel) data.get(i)).image,(ImageView) view);
                } else {//传过来的是  id了或者   url了
                    view = new ImageView(context);
                    setScaleType((ImageView) view);
                    // TODO: 2017/7/4  加载图片的方法   使用glide
                    ImageLoadUtils.load(context,((BannerModel) data.get(i)).image,(ImageView) view);
                }
                break;
        }
        if (mOnPageClickListener != null) {
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnPageClickListener.onPageClick(v, finalI);
                }
            });
        }
        container.addView(view);
        return view;
    }

    private void setScaleType(ImageView view) {
        setScaleType(view, ImageView.ScaleType.CENTER_CROP);
    }

    private void setScaleType(ImageView view, ImageView.ScaleType type) {
        view.setScaleType(type);
    }
}
