package com.alg.ailigou.pages.mall.comment.adapter;

import android.content.Context;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.utils.ImageUtils;

import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.comment.adapter
 * Created by Chris Chen on 2017/8/4 13:15.
 * Explain:买家晒图
 */

public class ImageAdapter extends BaseRecyclerAdapter<List<String>, String, ImageHolder> {
    public ImageAdapter(Context mContext, List<String> mData) {
        super(mContext, mData);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_mall_goods_comment_image;
    }

    @Override
    protected ImageHolder createViewHolder(View itemView) {
        return new ImageHolder(itemView);
    }

    @Override
    protected void refreshView(ImageHolder holder, int position) {
        String item = getItem(position);
        ImageUtils.load(mContext, item, holder.image);

    }
}
