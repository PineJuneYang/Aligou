package com.alg.ailigou.library.widget.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Haihang
 * @version 1.0.0
 * @date 2017年7月4日
 */
public abstract class ViewPagerAdapter<T> extends PagerAdapter implements BannerViewPager.DataSetSubject {

    protected List<BannerViewPager.DataSetSubscriber> mSubscribers = new ArrayList<>();

    public List<T> getData() {
        return data;
    }

    protected List<T> data;


    protected OnPageClickListener mOnPageClickListener;
    protected int countType;
    protected Context context;

    /**
     * @param data
     * @param countType 0表示 只有几个,譬如 4个  滚到d到4的时候  往左边到1  继续滚动    1表示无限循环
     */
    public ViewPagerAdapter(Context context, List<T> data, int countType) {
        this.context = context;
        this.data = data;
        this.countType = countType;

    }

    @Override
    public int getCount() {
        if (data == null || data.size() == 0) {
            return 0;
        }
        return countType == 0 ? data.size() : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = myInstantiateItem(container, position);
        if (mOnPageClickListener != null) {
            final int finalPositon;
            if (countType == 0) {
                finalPositon = position;
            } else {
                finalPositon = position % data.size();
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnPageClickListener.onPageClick(v, finalPositon);
                }
            });
        }
        return view;
    }

    private int mChildCount = 0;


    public void notifyDataSetChanged( List<T> data) {
        this.data=data;
        mChildCount = data.size();
        notifySubscriber();
        super.notifyDataSetChanged();
    }

    /**
     * 更新数据刷新
     *
     * @param setData
     */
    public void setDataChange(List setData) {
        if (setData == null) {
            return;
        }
        if (data == setData) {
            notifyDataSetChanged();
            return;
        } else if (data == null) {
            data = new ArrayList<>();
            data.addAll(setData);
            notifyDataSetChanged();
        } else {
            data.clear();
            data.addAll(setData);
            notifyDataSetChanged();
        }

    }
    /**
     * 更新数据刷新
     *
     * @param setData
     */
    public void setDataChange(List setData,BannerViewPager viewPager) {
        setDataChange(setData);
        viewPager.setCount(setData.size());
    }
    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            if (data != null) {
                mChildCount--;
            }
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    public abstract View myInstantiateItem(ViewGroup container, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public void registerSubscriber(BannerViewPager.DataSetSubscriber subscriber) {
        mSubscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(BannerViewPager.DataSetSubscriber subscriber) {
        mSubscribers.remove(subscriber);
    }

    @Override
    public void notifySubscriber() {
        for (BannerViewPager.DataSetSubscriber subscriber : mSubscribers) {
            subscriber.update(getCount());
        }
    }

    public void setOnPageClickListener(OnPageClickListener onPageClickListener) {
        mOnPageClickListener = onPageClickListener;
    }

    public interface OnPageClickListener {
        void onPageClick(View view, int position);
    }


}



