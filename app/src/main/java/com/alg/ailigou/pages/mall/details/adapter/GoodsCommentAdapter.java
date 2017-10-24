package com.alg.ailigou.pages.mall.details.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommentModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.pages.mall.comment.adapter.ImageAdapter;
import com.alg.ailigou.pages.mall.details.holder.GoodsCommentHolder;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.home.entrance.adapter
 * Created by Chris Chen on 2017/7/6 17:52.
 * Explain:
 */

public class GoodsCommentAdapter extends BaseRecyclerAdapter<List<CommentModel>, CommentModel, GoodsCommentHolder> {



    public GoodsCommentAdapter(Context mContext) {
        super(mContext,null);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_item_mall_goods_details_comment;
    }

    @Override
    protected GoodsCommentHolder createViewHolder(View itemView) {
        return new GoodsCommentHolder(itemView);
    }

    @Override
    protected void refreshView(GoodsCommentHolder holder, int position) {
        CommentModel item = getItem(position);

//        ImageUtils.load(mContext, item.photo, holder.photo);
        ImageLoadUtils.setImageCircle(mContext,item.photo, holder.photo);
        holder.username.setText(item.name);
        holder.publishTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(item.time));
        holder.content.setText(item.content);
        holder.standard.setText(item.standard);


        if (item.images != null && item.images.size() != 0) {
            ImageAdapter imageAdapter = new ImageAdapter(mContext, item.images);
            holder.images.setVisibility(View.VISIBLE);
            holder.images.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            holder.images.setAdapter(imageAdapter);

        } else {
            holder.images.setVisibility(View.GONE);
        }

    }
}
