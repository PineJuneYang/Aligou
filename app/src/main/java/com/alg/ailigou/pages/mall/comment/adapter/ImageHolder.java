package com.alg.ailigou.pages.mall.comment.adapter;

import android.view.View;
import android.widget.ImageView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.holder.BaseRecyclerHolder;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.comment.adapter
 * Created by Chris Chen on 2017/8/4 13:16.
 * Explain:商品评论买家晒图
 */

public class ImageHolder extends BaseRecyclerHolder{
    @BindView(R.id.iv_mall_goods_comment_image)
    public ImageView image;
    public ImageHolder(View itemView) {
        super(itemView);
    }
}
