package com.alg.ailigou.library.widget.banner;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;

/**
 * A BannerViewPager owns its indicators and it can roll automatically.
 * BannerViewPager支持指示器以及自动轮播。
 *
 * @author 独孤航海
 * @version 1.1.0
 * @date 2017年7月4日
 */
public class BannerViewPager extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    public ViewPagerIndicator mIndicator;
    private ViewPagerAdapter mAdapter;
    private Context mContext;
    private int mCurrentPosition;

    //viewpager's rolling state
    private int mViewPagerScrollState;
    //by default,auto-rolling is on.
    private boolean isAutoRolling = true;
    //the interval between rollings
    private int mAutoRollingTime = 1000;
    private int mReleasingTime = 0;
    private boolean isFirstVisible;
    private static final int MESSAGE_AUTO_ROLLING = 0X1001;
    private static final int MESSAGE_AUTO_ROLLING_CANCEL = 0X1002;
    private boolean isHasIndicator;//是否有 指示器


    private int count;//子页面的总数；

    public BannerViewPager(Context context) {
        this(context, null);
    }

    /**
     * @param context
     * @param isHasIndicator
     */
    public BannerViewPager(Context context, boolean isHasIndicator, int mAutoRollingTime) {
        this(context, null);
        this.mAutoRollingTime = mAutoRollingTime;
        this.isHasIndicator = isHasIndicator;
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initViews();
    }


    private void initViews() {
        //initialize the viewpager
        mViewPager = new ViewPager(mContext);
        ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
        lp.width = ViewPager.LayoutParams.MATCH_PARENT;
        lp.height = ViewPager.LayoutParams.MATCH_PARENT;
        mViewPager.setLayoutParams(lp);
        //给指示器设置margin
        mIndicator = new ViewPagerIndicator(mContext);
        FrameLayout.LayoutParams indicatorlp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        indicatorlp.gravity = Gravity.BOTTOM | Gravity.CENTER;
        indicatorlp.bottomMargin = 20;
        mIndicator.setLayoutParams(indicatorlp);
        isFirstVisible = true;
    }

    public void setAutoRolling(boolean isAutoRolling) {
        this.isAutoRolling = isAutoRolling;
    }

    /**
     * 设置轮播间隔
     *
     * @param time
     */
    public void setAutoRollingTime(int time) {
        this.mAutoRollingTime = time;
    }

    public boolean isHasIndicator() {
        return isHasIndicator;
    }

    /**
     * 设置是否有指示器
     *
     * @param hasIndicator
     */
    public void setHasIndicator(boolean hasIndicator) {
        isHasIndicator = hasIndicator;
    }

    public void setAdapter(ViewPagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        addView(mViewPager);
        mAdapter = adapter;
        if (mAdapter.getData() != null && mAdapter.getData().size() > 0) {
            setCount(mAdapter.getData().size());
            mIndicator.setItemCount(mAdapter.getData().size());
        }
        if (isHasIndicator) {
            mAdapter.registerSubscriber(new DataSetSubscriber() {
                @Override
                public void update(int count) {
                    mIndicator.setItemCount(count);
                }
            });
            addView(mIndicator);
        }
        //start the auto-rolling task if needed
        if (isAutoRolling) {
            postDelayed(mAutoRollingTask, mAutoRollingTime);
        }

    }

    /**
     * If the current page is being dragged by the user,this method will be invoke.
     * And then it will call {@link ViewPagerIndicator#setPositionAndOffset}.
     *
     * @param position Position index of the first page currently being displayed.
     * @param offset   Value from [0, 1) indicating the offset from the page at position.
     */
    private void setIndicator(int position, float offset) {
        mIndicator.setPositionAndOffset(position, offset);
    }

    /**
     * 设置指示器padding
     *
     * @param padding
     */
    public void setIndicatorPadding(int padding) {
        mIndicator.setPadding(padding);
    }

    /**
     * 设置没有选择的指示器默认背景颜色
     *
     * @param color
     */
    public void setIndicatorNoSelectColor(int color) {
        mIndicator.setNoSelectColor(color);
    }

    /**
     * 设置当前选择的指示器默认背景颜色
     *
     * @param color
     */
    public void setIndicatorSelectColor(int color) {
        mIndicator.setSelectColor(color);
    }

    /**
     * 设置指示器半径
     *
     * @param mRadius
     */
    public void setIndicatorRadius(int mRadius) {
        mIndicator.setRadius(mRadius);
    }

    /**
     * This runnable decides the viewpager should roll to next page or wait.
     */
    private Runnable mAutoRollingTask = new Runnable() {
        @Override
        public void run() {
            int now = (int) System.currentTimeMillis();
            int timediff = mAutoRollingTime;
            if (mReleasingTime != 0) {
                timediff = now - mReleasingTime;
            }

            if (mViewPagerScrollState == ViewPager.SCROLL_STATE_IDLE) {
                if (mAdapter == null) {
                    return;
                }
                //if user's finger just left the screen,we should wait for a while.
                if (timediff >= mAutoRollingTime * 0.8) {
                    if (mCurrentPosition == mAdapter.getCount() - 1) {
                        mViewPager.setCurrentItem(0, true);
                    } else {
                        mViewPager.setCurrentItem(mCurrentPosition + 1, true);
                    }
                    postDelayed(mAutoRollingTask, mAutoRollingTime);
                } else {
                    postDelayed(mAutoRollingTask, mAutoRollingTime);
                }
            } else if (mViewPagerScrollState == ViewPager.SCROLL_STATE_DRAGGING) {
                postDelayed(mAutoRollingTask, mAutoRollingTime);
            }

        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (isHasIndicator) {
            if (isAutoRolling && count > 0) {
                setIndicator(position % count, positionOffset);
            } else {
                setIndicator(position, positionOffset);
            }

        }
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_DRAGGING) {
            mViewPagerScrollState = ViewPager.SCROLL_STATE_DRAGGING;
        } else if (state == ViewPager.SCROLL_STATE_IDLE) {
            mReleasingTime = (int) System.currentTimeMillis();
            mViewPagerScrollState = ViewPager.SCROLL_STATE_IDLE;
        }

    }

    /**
     * 每当当前窗口被隐藏、至于后台时，Runnable就会停止并被取消，防止内存泄漏。
     *
     * @param visibility
     */
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (!isAutoRolling) return;
        if (visibility != VISIBLE) {
            Log.d("cylog", "remove callbacks");
            removeCallbacks(mAutoRollingTask);
        } else {
            if (isFirstVisible) {
                isFirstVisible = false;
            } else {
                Log.d("cylog", "post runnable");
                postDelayed(mAutoRollingTask, mAutoRollingTime);
            }
        }


    }

    /**
     * Save the state of this BannerViewPager.The current position will be saved.
     *
     * @return Parcelable
     */
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable = super.onSaveInstanceState();
        SavedState ss = new SavedState(parcelable);
        ss.currentPosition = mCurrentPosition;
        return ss;
    }

    /**
     * Restore the BannerViewPager from the previous state.
     *
     * @param state
     */
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        mViewPager.setCurrentItem(ss.currentPosition);
    }

    static class SavedState extends BaseSavedState {

        int currentPosition;

        public SavedState(Parcel source) {
            super(source);
            currentPosition = source.readInt();
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(currentPosition);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {

            @Override
            public SavedState createFromParcel(Parcel source) {
                return new SavedState(source);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        mIndicator.setItemCount(count);
    }

    /**
     * 数据发生变化时 增添指示器原点
     */
    public interface DataSetSubject {
        void registerSubscriber(DataSetSubscriber subscriber);

        void removeSubscriber(DataSetSubscriber subscriber);

        void notifySubscriber();


    }

    /**
     * 数据发生变化时回调
     */
    public interface DataSetSubscriber {
        void update(int count);
    }
}
