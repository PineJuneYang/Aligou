package com.alg.ailigou.common.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 海航
 * on 2017/8/21.
 * 此类或接口用于 设置recylerview 的间距
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    /**
     *
     * @param space
     *
     */
    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;
        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) % parent.getChildCount() == 0) {
            outRect.left = 0;
        }
    }

}


