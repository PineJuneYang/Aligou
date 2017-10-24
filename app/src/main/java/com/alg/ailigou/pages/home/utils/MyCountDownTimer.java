package com.alg.ailigou.pages.home.utils;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.utils
 * Created by Chris Chen on 2017/7/27 15:47.
 * Explain:倒计时组件
 */

public class MyCountDownTimer extends CountDownTimer {
    private OnTimeTickListener onTimeTickListener;

    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (onTimeTickListener != null) {
            onTimeTickListener.onTimeTike(millisUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        if (onTimeTickListener != null) {
            onTimeTickListener.onFinished();
        }
    }

    public void setOnTimeTickListener(OnTimeTickListener onTimeTickListener) {
        this.onTimeTickListener = onTimeTickListener;
    }
}
