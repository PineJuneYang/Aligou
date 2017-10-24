package com.alg.ailigou.pages.home.utils;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.utils
 * Created by Chris Chen on 2017/7/27 15:42.
 * Explain:倒计时组件基类
 */
public abstract class CountDownTimer {

    private final long mMillisInFuture;
    private final long mCountdownInterval;
    private long mStopTimeInFuture;

    private static final int MSG = 1;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (CountDownTimer.this) {
                final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();

                if (millisLeft <= 0) {
                    onFinish();
                } else if (millisLeft < mCountdownInterval) {
                    sendMessageDelayed(obtainMessage(MSG), millisLeft);
                } else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    onTick(millisLeft);
                    long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();
                    while (delay < 0) delay += mCountdownInterval;
                    sendMessageDelayed(obtainMessage(MSG), delay);
                }
            }
        }
    };


    /**
     * @param millisInFuture    倒计时的总时间，毫秒
     * @param countDownInterval 倒计时间隔的时间 毫秒
     */
    public CountDownTimer(long millisInFuture, long countDownInterval) {
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
    }

    /**
     * 取消倒计时
     */
    public final void cancel() {
        mHandler.removeMessages(MSG);
    }

    /**
     * 开始倒计时
     */

    public synchronized final CountDownTimer start() {
        if (mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }

    //倒计时
    public abstract void onTick(long millisUntilFinished);

    //计时结束
    public abstract void onFinish();

    /**
     * 倒计时的监听
     */
    public interface OnTimeTickListener {
        //倒计时
        void onTimeTike(long millisSecond);

        //倒计时结束
        void onFinished();
    }
}