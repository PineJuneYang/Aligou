package com.alg.ailigou.common.utils;

import android.content.Context;

/**
 * com.alg.ailigouapp.common.utils
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 10:29
 * Explain:应用工具类
 */

public class AppUtils {
    public static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getLocalContext() {
        return mContext;
    }
}
