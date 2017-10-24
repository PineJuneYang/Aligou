package com.alg.ailigou.pages.news.fragments.holder;

import android.view.View;
import android.widget.ImageView;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;

/**
 * Created by 海航
 * on 2017/7/13.
 * 此类或接口用于  新闻条目只有一个图片item
 */

public class NewsItemTitleImageHolder extends MyBaseViewHolder {
    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    private ImageView iv;

    public NewsItemTitleImageHolder(View itemView, OnItemClickListener listener) {
        super(itemView, listener);
        iv = (ImageView) itemView.findViewById(R.id.iv_news);
        this.iv = iv;
    }
}
