package com.alg.ailigou.common.utils;

import in.srain.cube.views.ptr.RefreshFrameLayout;

/**
 * Created by 海航
 * on 2017/9/5.
 * 此类或接口用于刷新加载完成
 */

public class RefreshCompleteUtils {
    public static void refreshComplete(RefreshFrameLayout mRefreshFrameLayout) {
        if (mRefreshFrameLayout != null && mRefreshFrameLayout.isRefreshing()) {
            mRefreshFrameLayout.refreshComplete();
        }
    }
}
