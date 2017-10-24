package com.alg.ailigou.common.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by hai on 2017/1/12.
 */

public class PopuWindowUtils {
    public static PopupWindow getPopuWindow(View view, boolean isFocusable) {
        PopupWindow mPopuWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopuWindow.setFocusable(isFocusable);// 加上这个popupwindow中的ListView才可以接收点击事件
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        mPopuWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopuWindow.setOutsideTouchable(true);
        return mPopuWindow;
    }

    /**
     * @param dialog
     * @param
     * @param w      宽的多少倍
     * @param h      高的多少倍
     */
    public static void setPopupWindowWH(Dialog dialog, Activity activity, float w, float h) {
        WindowManager manager = activity.getWindowManager();
        Display d = manager.getDefaultDisplay();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if (h != 0) {
            params.height = (int) (d.getHeight() * h);
        }
        params.width = d.getWidth();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }
}
