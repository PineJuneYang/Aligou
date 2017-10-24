package com.alg.ailigou.pages.cart.adapter;

import android.view.View;

/**
 * Created by 海航
 * on 2017/7/12.
 * 此类或接口用于
 */

public interface OnShoppingCartDataChangedListener {
    //点击加号按钮
    void setOnAddGoodsCountListener(View view, int position);

    //点击减号按钮
    void setOnDeleteGoodsCountListener(View view, int position);

    //点击选择按钮
    void setOnGoodsSelectListener(View view, int position);
}
